package src.main.java.com.theparanoid.sonarqube;

public class AppContext {

	private Boolean isDockerUp;
	private static AppContext context;
	
	private AppContext() {
		super();
		isDockerUp = false;
	}
	
	public void setDockerStatus(Boolean status) {
		this.isDockerUp = status;
	}
	
	public Boolean getDockerStatus() {
		return this.isDockerUp;
	}
	
	public static AppContext getContext() {
		if(context == null) {
			context = new AppContext();
		} 
		return context;
	}
	
}
