package core;

import java.util.List;

public class ExceptionNode {

	private String exception;
	private List<ContextNode> contexts;

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public List<ContextNode> getContexts() {
		return contexts;
	}

	public void setContexts(List<ContextNode> contexts) {
		this.contexts = contexts;
	}	
	
}
