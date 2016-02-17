package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.EventNode;
import core.Graph;
import core.Visitor;
import factories.RobotiumMethodFactory;
import generator.Generator;
import util.RobotiumMethodsMap;

/**
 * Transform the Android events into Robotuim events 
 * @author Ana Gissel
 */
public class EventTransformer implements Visitor{

	@Override
	public void visit(Graph graph) {
		RobotiumTestClassGenerator clsGen = new RobotiumTestClassGenerator();
		//Transform the android events
		List<String> robotiumMethods = getRobotiumMethods(graph.getEvents());
		//Generate the robotium test		
		clsGen.generateRobotiumTest(graph.getAppActivity().getActivity(),robotiumMethods,Generator.fileOutput);
	}
	
	/**
	 * Returns the list of equivalent Robotium methods for the given android events list
	 * @param androidEvents
	 * @return
	 */
	public List<String> getRobotiumMethods(List<EventNode> androidEvents){
		List<String> robotiumActions = new ArrayList<String>();
		
		for(EventNode event : androidEvents){
			robotiumActions.add(getActionCode(event.getEventParameters()));
		}
		return robotiumActions;
	}
	
	/**
	 * Returns the corresponding Robotium method code for the Android event. 
	 * @param androidAction
	 * @return
	 */
	private String getActionCode(HashMap<String, String> methodParametersMap)
	{
		String action="";
				
		try {
			Object factory = RobotiumMethodFactory.class.newInstance();		
			String instance = verifyInstance(methodParametersMap.get("instanceOf"), methodParametersMap);
			Method methodFactory = RobotiumMethodsMap.getMethod(methodParametersMap.get("action"),instance);
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
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException |
				SecurityException | NoSuchMethodException e) {			
			e.printStackTrace();
			return action;
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
