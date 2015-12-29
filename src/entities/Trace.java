package entities;

import java.util.HashMap;
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
	private List<HashMap<String,String>> events;
	
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
	
	public List<HashMap<String,String>> getEvents() {
		return events;
	}
	
	public void setEvents(List<HashMap<String,String>> events) {
		this.events = events;
	}
	
	public String getException() {
		return exception;
	}
	
	public void setException(String exception) {
		this.exception = exception;
	}	
}
