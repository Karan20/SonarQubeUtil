package src.main.java.com.theparanoid.sonarqube;

import src.main.java.com.theparanoid.sonarqube.util.Util;

// Only for testing purpose 
// Use App class in ui package
public class MainClass {
	
	public static void main(String[] args) {
		// future scope : put it into log file
		System.out.println("Starting main");
		Util util = new Util();
		util.isSonarContainerExistAndRunning();
	}

}
