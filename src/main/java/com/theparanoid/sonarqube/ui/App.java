package src.main.java.com.theparanoid.sonarqube.ui;

import src.main.java.com.theparanoid.sonarqube.constant.Constant;

public class App {
	
	public App() {
		super();
		new Windows(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT, Constant.WINDOW_TITLE, this);
	}
	
	public static void main(String[] args) {
		new App();
	}

}
