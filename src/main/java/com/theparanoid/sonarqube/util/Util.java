package src.main.java.com.theparanoid.sonarqube.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.java.com.theparanoid.sonarqube.AppContext;
import src.main.java.com.theparanoid.sonarqube.constant.Constant;

public class Util {
	
	private AppContext context;
	
	public Map<String, Boolean> checkDockerStatus() {
		try {
			Process process = Runtime.getRuntime().exec(Constant.DOCKER_INFO);
			String log = getLogs(process);
			if(process.isAlive()){
				process.destroyForcibly();
			}
			Map<String, Boolean> dockerStatus = checkDockerStatus(log);
			System.out.println(Constant.IS_DOCKER_CLIENT_UP + " : "+dockerStatus.get(Constant.IS_DOCKER_CLIENT_UP));
			System.out.println(Constant.IS_DOCKER_SERVER_UP + " : "+dockerStatus.get(Constant.IS_DOCKER_SERVER_UP));
			return dockerStatus;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getLogs(Process process) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = "";
		StringBuilder log = new StringBuilder();
		while((line= reader.readLine())!= null) {
			log.append(line);
			// future scope : put it into log file
			System.out.println(line);
		}
		return log.toString();
	}
	
	private Map<String,Boolean> checkDockerStatus(String log) {
		Map<String, Boolean> result = new HashMap<>();
		boolean isClientUp = false;
		boolean isServerUp = false;
		
		Pattern clientPattern = Pattern.compile("Client");
		Pattern serverPattern = Pattern.compile("Server");
		Pattern connectionPattern = Pattern.compile("Cannot connect");
		
		Matcher matcher = clientPattern.matcher(log);
		Matcher connectionMatcher = connectionPattern.matcher(log);
		if(matcher.find()) {
			isClientUp = true;
		}
		matcher = serverPattern.matcher(log);
		if(matcher.find()&& !(connectionMatcher.find())) {
			isServerUp = true;
		}
		result.put(Constant.IS_DOCKER_CLIENT_UP, isClientUp);
		result.put(Constant.IS_DOCKER_SERVER_UP, isServerUp);
		return result;
	}
	
	private void initContext() {
		if(context == null) {
			context = AppContext.getContext();
		}
	}
	
	public void isSonarContainerExistAndRunning() {
		initContext();
		if(!context.getDockerStatus()) {
			try {
				Process process = Runtime.getRuntime().exec(Constant.DOCKER_PS_ALL);
				getLogs(process);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// return false for everything
		}
	}

}
