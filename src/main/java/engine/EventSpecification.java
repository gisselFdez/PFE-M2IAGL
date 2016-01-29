package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import factories.TextualMethodFactory;
import util.TextualMethodsMap;

/**
 * Generates a natural language specification for the given Android methods
 * @author Ana Gissel
 *
 */
public class EventSpecification {

	/**
	 * Returns the list of equivalent Robotium methods for the given android events list
	 * @param androidEvents
	 * @return
	 */
	public List<String> getTextualSpecification(List<HashMap<String,String>> androidEvents){
		List<String> textualActions = new ArrayList<String>();
		
		for(HashMap<String,String> parametersMap : androidEvents){
			textualActions.add(getActionCode(parametersMap));
		}
		return textualActions;
	}
	
	/**
	 * Returns the corresponding textual specification for the Android event. 
	 * @param androidAction
	 * @return
	 */
	private String getActionCode(HashMap<String, String> methodParametersMap)
	{
		String action="";
				
		try {
			Object factory = TextualMethodFactory.class.newInstance();		
			String instance = verifyInstance(methodParametersMap.get("instanceOf"), methodParametersMap);
			Method methodFactory = TextualMethodsMap.getMethod(methodParametersMap.get("action"),instance);
			if(methodFactory!=null){
				Object act = methodFactory.invoke(factory,methodParametersMap);
				action = act.toString();
				System.out.println("return: "+act);
			}
			else{
				if(methodParametersMap.get("action").equals("onClick") && methodParametersMap.get("mID")!=null){
					Class<?>[] paramTypes = {HashMap.class};
					methodFactory = TextualMethodFactory.class.getDeclaredMethod("clickOnView",paramTypes);
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
