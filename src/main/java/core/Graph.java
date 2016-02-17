package core;

import java.util.HashMap;
import java.util.List;

/**
 * The Graph class describes the application crash scenario
 * @author Ana Gissel
 *
 */
public class Graph {

	/**
	 * The node with the name of the application activity
	 */
	private ActivityNode appActivity;
	
	/**
	 * The list of EventNodes (Android events) to reproduce the crash
	 */
	private List<EventNode> events;
	
	/**
	 * The node with the exception produced in the crash escenario
	 */
	private ExceptionNode exception;	
	
	
	public ActivityNode getAppActivity() {
		return appActivity;
	}
	
	public void setAppActivity(ActivityNode appActivity) {
		this.appActivity = appActivity;
	}
	
	public List<EventNode> getEvents() {
		return events;
	}
	
	public void setEvents(List<EventNode> events) {
		this.events = events;
	}
	
	public ExceptionNode getException() {
		return exception;
	}
	
	public void setException(ExceptionNode exception) {
		this.exception = exception;
	}	
}
