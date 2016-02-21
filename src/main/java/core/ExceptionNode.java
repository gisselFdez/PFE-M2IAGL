package core;

import java.util.List;

public class ExceptionNode {

	private String exception;
	private List<StaticContextNode> contexts;
	private List<DynamicContextNode> dynamicContexts;
	
	public List<DynamicContextNode> getDynamicContexts() {
		return dynamicContexts;
	}

	public void setDynamicContexts(List<DynamicContextNode> dynamicContexts) {
		this.dynamicContexts = dynamicContexts;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public List<StaticContextNode> getContexts() {
		return contexts;
	}

	public void setContexts(List<StaticContextNode> contexts) {
		this.contexts = contexts;
	}	
	
}
