package core;

import java.util.HashMap;
import java.util.List;

public class EventNode {

	private HashMap<String,String> eventParameters;
	private List<DynamicContextNode> dynamicContext;
	
	public List<DynamicContextNode> getDynamicContext() {
		return dynamicContext;
	}

	public void setDynamicContext(List<DynamicContextNode> dynamicContext) {
		this.dynamicContext = dynamicContext;
	}

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
