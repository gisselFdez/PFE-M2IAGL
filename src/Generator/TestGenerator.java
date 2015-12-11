package Generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;

import util.MethodFactory;
import util.MethodsMap;

public class TestGenerator {

	String appName="";
		
	/**
	 * Generates the corresponding Robotium test
	 * @param n_column
	 */
	public void GenerateRobotiumTest(Iterator<Path> n_column){				
		//Treat the DB information
		List<String> actions = treatInformation(n_column); 
		
		//Generate Test code
		RobotiumTestCodeGenerator clsGen = new RobotiumTestCodeGenerator();
		clsGen.generateRobotiumTest(appName, actions);
	}
		
	/**
	 * Treat the DB information 
	 * @param n_column
	 * @return
	 */
	private List<String> treatInformation(Iterator<Path> n_column){
		List<String> actions = new ArrayList<String>();
		
		//Generate code for every node
		while(n_column.hasNext()){
			Path path = n_column.next();
			
			//Get all Nodes from path
			Iterable<Node> nodes = path.nodes();
			for (Node node : nodes)
    		{
				System.out.println("NODE-------: ");
				for (String key : node.getPropertyKeys()) {
					System.out.println("KEY: "+key);
					if(key.equals("app")){
						appName = node.getProperty(key).toString();
					}
					if(!key.equals("app") && !key.equals("exception") && !key.equals("counter")){
						actions.add(getActionCode(node.getProperty(key).toString()));
					}
            	}
    		}					
        }
		return actions;
	}
	
	/**
	 * Invoke the corresponding method to generate the Node action code.
	 * @param nodeValue
	 * @return
	 */
	private String getActionCode(String nodeValue)
	{
		HashMap<String, String> methodParametersMap = generateParametersMap(nodeValue);
		String action="";
				
		try {
			Object factory = MethodFactory.class.newInstance();		
			Method methodFactory = MethodsMap.getMethod(methodParametersMap.get("action"),methodParametersMap.get("instanceOf"));
			if(methodFactory!=null){
				Object act = methodFactory.invoke(factory,methodParametersMap);
				action = act.toString();
				System.out.println("return: "+act);
			}
			else{
				if(methodParametersMap.get("action").equals("onClick") && methodParametersMap.get("mID")!=null){
					Class<?>[] paramTypes = {HashMap.class};
					methodFactory = MethodFactory.class.getDeclaredMethod("clickOnView",paramTypes);
					Object act = methodFactory.invoke(factory,methodParametersMap);
					action = act.toString();
					System.out.println("return: "+act);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {			
			e.printStackTrace();
			return action;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return action;
	}
	
	/**
	 * Generates the parametersMap for the node action
	 * @param nodeValue
	 * @return
	 */
	private HashMap<String, String> generateParametersMap(String nodeValue){
		HashMap<String, String> methodParametersMap = new HashMap<String, String>();
		
		String[] values = nodeValue.replace("{", "").replace("}", "").split(", ");	
		
		//get all the action parameters
		for(String value : values)
		{
			System.out.println("value"+value);
			String[] param = value.split("=");
			if(param[0].contains("mID"))
				methodParametersMap.put("mID", param[1]);
			if(param[0].contains("mText"))
				methodParametersMap.put("mText", param[1]);		
			if(param[0].contains("mTitle"))
				methodParametersMap.put("mTitle", param[1]);		
			if(param[0].contains("location"))
				methodParametersMap.put("location", param[1]);
			if(param[0].contains("position"))
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
			}	
		}
		return methodParametersMap;
	}
}
