package src.main.java.com.theparanoid.sonarqube.constant;

public class Constant {
	
	public static final String DOCKER_INFO = "docker info";
	public static final String DOCKER_PS = "docker ps -f name=sonar";
	public static final String DOCKER_PS_ALL = "docker ps --all -f name=sonar";
	
	public static final String IS_DOCKER_CLIENT_UP = "Is Docker Client Up";
	public static final String IS_DOCKER_SERVER_UP = "Is Docker Server Up";
	
	public static final String WINDOW_TITLE = "SonarQube Util";
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = WINDOW_WIDTH/12*9;

}
