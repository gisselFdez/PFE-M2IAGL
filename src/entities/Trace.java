package entities;

import java.util.List;

/**
 * The Trace class describes the application crash scenario
 * @author Ana Gissel
 *
 */
public class Trace {

	/**
	 * The name of the application activity
	 */
	private String appActivity;
	
	/**
	 * The list of Android events to reproduce the crash
	 */
	private List<String> events;
	
	/**
	 * The exception produced in the crash escenario
	 */
	private String exception;	
	
	
	public String getAppActivity() {
		return appActivity;
	}
	
	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}
	
	public List<String> getActions() {
		return events;
	}
	
	public void setActions(List<String> actions) {
		this.events = actions;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}	
}
