package factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;

import core.ActivityNode;
import core.DynamicContextNode;
import core.StaticContextNode;
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
		List<EventNode> events = new ArrayList<EventNode>();
		List<StaticContextNode> context = new ArrayList<StaticContextNode>();
		
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
						if(lbl.name().equals("USER_EVENT")){
							EventNode event = generateEvent(node.getProperty("id").toString());
							List<DynamicContextNode> contextList = new ArrayList<DynamicContextNode>();
							//Get the relationships (of type WITH_DYNAMIC_CONTEXT) of the USER_EVENT node
							Iterable<Relationship> node_relationships=node.getRelationships(Direction.OUTGOING);
							//For each relationship get the DYNAMIC_CONTEXT node
	                        for(Relationship rel : node_relationships){ 
	                        	//System.out.println(rel.getType().toString());
	                        	if(rel.getType().toString().contains("WITH_DYNAMIC_CONTEXT")){
	                        		Node dynamicContextNode= rel.getEndNode();
		                            //Get the properties of the DYNAMIC CONTEXT node
		                            String network=dynamicContextNode.getProperty("network").toString();
		                            String netType=dynamicContextNode.getProperty("netType").toString();
		                            String gps=dynamicContextNode.getProperty("gps").toString();
		                            DynamicContextNode dynCntx = new DynamicContextNode();
		                            dynCntx.setNetwork(network);
		                            dynCntx.setNetworkType(netType);
		                            dynCntx.setGps(gps);
		                            contextList.add(dynCntx);
	                        	}	                            
	                        }
	                        event.setDynamicContext(contextList);
	                        events.add(event);
						}
						if(lbl.name().equals("CRASH_EVENT") && !crashFound){
							crashFound = true;
							//trace.setException(new ExceptionNode(node.getProperty("exception").toString()));
							List<DynamicContextNode> contextList = new ArrayList<DynamicContextNode>();
							//Get the relationships (of type WITH_DYNAMIC_CONTEXT) of the USER_EVENT node
							Iterable<Relationship> node_relationships=node.getRelationships(Direction.OUTGOING);
							//For each relationship get the DYNAMIC_CONTEXT node
	                        for(Relationship rel : node_relationships){ 
	                        	//System.out.println(rel.getType().toString());
	                        	if(rel.getType().toString().contains("WITH_DYNAMIC_CONTEXT")){
	                        		Node dynamicContextNode= rel.getEndNode();
		                            //Get the properties of the DYNAMIC CONTEXT node
		                            String network=dynamicContextNode.getProperty("network").toString();
		                            String netType=dynamicContextNode.getProperty("netType").toString();
		                            String gps=dynamicContextNode.getProperty("gps").toString();
		                            DynamicContextNode dynCntx = new DynamicContextNode();
		                            dynCntx.setNetwork(network);
		                            dynCntx.setNetworkType(netType);
		                            dynCntx.setGps(gps);
		                            contextList.add(dynCntx);
	                        	}	                            
	                        }
	                        exception.setDynamicContexts(contextList);
							exception.setException(node.getProperty("exception").toString());
						}
						if(lbl.name().equals("STATIC_CONTEXT"))
							context.add(new StaticContextNode(node.getProperty("androidSDK").toString(),
									node.getProperty("manufacturer").toString()));							
					}					
				}
    		}			
        }		
		
		trace.setEvents(events);
		exception.setContexts(removeDuplicates(context));
		trace.setException(exception);
		return trace;
	}
	
	/**
	 * Generates a list of Android actions with its parameters map from a given actions list 
	 * @param events
	 * @return 
	 */
	private EventNode generateEvent(String event){				
			HashMap<String, String> methodParametersMap = generateParametersMap(event);
			return new EventNode(methodParametersMap);
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
	private List<StaticContextNode> removeDuplicates(List<StaticContextNode> contexts){
		Set set = new TreeSet(new ContextComparator());
		set.addAll(contexts);

		List<StaticContextNode> c =new ArrayList(set);
	    System.out.println(c);
	    return c;
	}	
	
}
