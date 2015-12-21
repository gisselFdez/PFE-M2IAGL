package Entities;

import java.util.List;

public class Trace {

	private String appActivity;
	private List<String> actions;
	private String exception;	
	
	public String getAppActivity() {
		return appActivity;
	}
	
	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}
	
	public List<String> getActions() {
		return actions;
	}
	
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}	
}
