package factories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;

import core.ActivityNode;
import core.ContextNode;
import core.EventNode;
import core.ExceptionNode;
import core.Graph;
import util.ContextComparator;

/**
 * Contains all the methods that treat the DB information to transform it into a Trace object
 * @author Ana Gissel
 *
 */
public class TraceFactory {

	/**
	 * Treat the DB information to transform it into a Trace object
	 * @param n_column
	 * @return the Trace
	 */
	public Graph getTrace(Iterator<Path> n_column){
		Boolean crashFound = false;
		ExceptionNode exception = new ExceptionNode();
		Graph trace = new Graph();
		List<String> events = new ArrayList<String>();
		List<ContextNode> context = new ArrayList<ContextNode>();
		
		//Generate code for every node
		while(n_column.hasNext()){
			Path path = n_column.next();
			
			//Get all Nodes from path
			Iterable<Node> nodes = path.nodes();
			for (Node node : nodes)
    		{
				Iterable<Label> labels = node.getLabels();
				if(labels!=null){
					for(Label lbl:labels){
						if(lbl.name().equals("APP"))
							trace.setAppActivity(new ActivityNode(node.getProperty("app").toString()));
						if(lbl.name().equals("USER_EVENT"))
							events.add(node.getProperty("id").toString());						
						if(lbl.name().equals("CRASH_EVENT") && !crashFound){
							crashFound = true;
							//trace.setException(new ExceptionNode(node.getProperty("exception").toString()));
							exception.setException(node.getProperty("exception").toString());
						}
						if(lbl.name().equals("STATIC_CONTEXT"))
							context.add(new ContextNode(node.getProperty("androidSDK").toString(),
									node.getProperty("manufacturer").toString()));							
					}					
				}
    		}					
        }		
		trace.setEvents(generateEvents(events));
		exception.setContexts(removeDuplicates(context));
		trace.setException(exception);
		return trace;
	}
	
	/**
	 * Generates a list of Android actions with its parameters map from a given actions list 
	 * @param events
	 * @return 
	 */
	private List<EventNode> generateEvents(List<String> events){		
		List<EventNode> androidEvents = new ArrayList<EventNode>();
		
		for(String event : events){
			HashMap<String, String> methodParametersMap = generateParametersMap(event);
			androidEvents.add(new EventNode(methodParametersMap));
		}
		return androidEvents;
	}
	
	/**
	 * Transforms the attributes in an android event into a parameters dictionary 
	 * @param androidEvent
	 * @return the parameters dictionary
	 */
	private HashMap<String, String> generateParametersMap(String androidEvent){
		HashMap<String, String> methodParametersMap = new HashMap<String, String>();
		
		String[] values = androidEvent.replace("{", "").replace("}", "").split(", ");	
		
		//get all the action parameters
		for(String value : values)
		{
			System.out.println("value: "+value);
			String[] param = value.split("=");
			if(param[0].contains("mID"))
				methodParametersMap.put("mID", param[1]);
			if(param[0].contains("mText"))
				methodParametersMap.put("mText", param[1]);		
			if(param[0].contains("mTitle"))
				methodParametersMap.put("mTitle", param[1]);		
			if(param[0].contains("location"))
				methodParametersMap.put("location", param[1]);							
			if(param[0].contains("position") || param[0].contains("Position"))
				methodParametersMap.put("position", param[1]);
			if(param[0].contains("id"))
				methodParametersMap.put("id", param[1]);	
			if(param[0].contains("keyCode"))
				methodParametersMap.put("keyCode", param[1]);
			if(param[1].contains("instance of android")){
				methodParametersMap.put("instance of android", param[1]);
				String[] elements = param[1].split("\\.");
				methodParametersMap.put("instanceOf", elements[(elements.length)-1]);
			}
			if(param[1].contains("instance of com.android")){
				methodParametersMap.put("instance of android", param[1]);
				String[] elements = param[1].split("\\.");
				methodParametersMap.put("instanceOf", elements[(elements.length)-1]);
			}
			if(param[0].contains("location")){
				methodParametersMap.put("location", param[1]);
				String[] elements = param[1].split("\\.");
				methodParametersMap.put("action", elements[(elements.length)-1]);
				methodParametersMap.put("parenAction", elements[(elements.length)-2]);
			}	
		}
		return methodParametersMap;
	}
	
	/**
	 * Remove the duplicates context nodes with the same manufacturer
	 * @param contexts
	 * @return
	 */
	private List<ContextNode> removeDuplicates(List<ContextNode> contexts){
		System.out.println(contexts);

		Set set = new TreeSet(new ContextComparator());
		set.addAll(contexts);

		System.out.println("\n***** After removing duplicates *******\n");

	    List<ContextNode> c =new ArrayList(set);
	    System.out.println(c);
	    return c;

	}	
	
}
