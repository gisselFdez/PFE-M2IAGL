package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;

import entities.Trace;
import util.MethodsMap;
import util.RobotiumMethodFactory;

/**
 * Transform the Android events into Robotuim events 
 * @author Ana Gissel
 *
 */
public class TestGenerator {

	String appActivity="";
	String fileOutput;
		
	public TestGenerator(String fileOutput){
		this.fileOutput = fileOutput;
	}
	/**
	 * Generates the corresponding Robotium test
	 * @param n_column
	 */
	public void GenerateRobotiumTest(Trace trace){		
		//Generate Test code
		RobotiumTestClassGenerator clsGen = new RobotiumTestClassGenerator();
		clsGen.generateRobotiumTest(appActivity, getRobotiumMethods(trace.getEvents()),fileOutput);
	}		
	
	/**
	 * Returns the list of equivalent Robotium methods for the given android events list
	 * @param androidEvents
	 * @return
	 */
	private List<String> getRobotiumMethods(List<HashMap<String,String>> androidEvents){
		List<String> robotiumActions = new ArrayList<String>();
		
		for(HashMap<String,String> parametersMap : androidEvents){
			robotiumActions.add(getActionCode(parametersMap));
		}
		return robotiumActions;
	}
	
	/**
	 * get the corresponding Robotium method code for the Android event.
	 * @param androidAction
	 * @return
	 */
	private String getActionCode(HashMap<String, String> methodParametersMap)
	{
		String action="";
				
		try {
			Object factory = RobotiumMethodFactory.class.newInstance();		
			String instance = verifyInstance(methodParametersMap.get("instanceOf"), methodParametersMap);
			Method methodFactory = MethodsMap.getMethod(methodParametersMap.get("action"),instance);
			if(methodFactory!=null){
				Object act = methodFactory.invoke(factory,methodParametersMap);
				action = act.toString();
				System.out.println("return: "+act);
			}
			else{
				if(methodParametersMap.get("action").equals("onClick") && methodParametersMap.get("mID")!=null){
					Class<?>[] paramTypes = {HashMap.class};
					methodFactory = RobotiumMethodFactory.class.getDeclaredMethod("clickOnView",paramTypes);
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
	 * Verify if the instance is a Layout
	 * @param instanceOf
	 * @param methodParametersMap
	 * @return
	 */
	private String verifyInstance(String instanceOf,HashMap<String, String> methodParametersMap){
		if(instanceOf.contains("Layout"))
			instanceOf = methodParametersMap.get("parenAction");
		return instanceOf;
	}	
}
