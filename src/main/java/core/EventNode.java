package core;

import java.util.HashMap;
import java.util.List;

public class EventNode {

	private HashMap<String,String> eventParameters;

	public HashMap<String, String> getEventParameters() {
		return eventParameters;
	}

	public void setEventParameters(HashMap<String, String> eventParameters) {
		this.eventParameters = eventParameters;
	}
	
	public EventNode(HashMap<String, String> parameters){
		this.eventParameters = parameters;
	}
	
}
