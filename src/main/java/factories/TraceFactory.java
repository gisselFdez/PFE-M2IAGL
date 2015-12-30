package factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;

import entities.Trace;

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
	public Trace getTrace(Iterator<Path> n_column){
		Trace trace = new Trace();
		List<String> events = new ArrayList<String>();
		
		//Generate code for every node
		while(n_column.hasNext()){
			Path path = n_column.next();
			
			//Get all Nodes from path
			Iterable<Node> nodes = path.nodes();
			for (Node node : nodes)
    		{
				for (String key : node.getPropertyKeys()) {
					if(key.equals("app"))
						trace.setAppActivity(node.getProperty(key).toString());					
					if(!key.equals("app") && !key.equals("exception") && !key.equals("counter"))
						events.add(node.getProperty(key).toString());					
					if(key.equals("exception"))
						trace.setException(node.getProperty(key).toString());
            	}
    		}					
        }
		trace.setEvents(generateEvents(events));
		return trace;
	}
	
	/**
	 * Generates a list of Android actions with its parameters map from a given actions list 
	 * @param events
	 * @return 
	 */
	private List<HashMap<String,String>> generateEvents(List<String> events){		
		List<HashMap<String,String>> androidEvents = new ArrayList<HashMap<String,String>>();
		
		for(String event : events){
			HashMap<String, String> methodParametersMap = generateParametersMap(event);
			androidEvents.add(methodParametersMap);
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
}
