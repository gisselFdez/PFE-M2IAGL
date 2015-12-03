package util;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MethodsRobotiumMap {

	public static MethodsRobotiumMap myMap = new MethodsRobotiumMap();	
	private static HashMap<String, HashMap<String, String>> robotiumMethodMap;

	private MethodsRobotiumMap(){
		robotiumMethodMap = new HashMap<String, HashMap<String, String>>();
		
		//onClick
		HashMap<String, String> instancesOnClick = new HashMap<String, String>();
		instancesOnClick.put("Button", "clickOnButton");
		instancesOnClick.put("ImageView", "clickOnView");		
		robotiumMethodMap.put("onClick", instancesOnClick);
		
		//performItemClick	
		HashMap<String, String> instancesPerformClick = new HashMap<String, String>();
		instancesPerformClick.put("ListMenuItemView", "pressMenuItem");
		robotiumMethodMap.put("performItemClick", instancesPerformClick);
	}		
	
	/**
	 * Returns the actual instance
	 * @return
	 */
	public MethodsRobotiumMap getInstance(){
		return myMap;
	}
	
	/**
	 * Retutns the RobotiumMethodMap
	 * @return
	 */
	private HashMap<String, HashMap<String, String>> getRobotiumMethodMap(){
		return robotiumMethodMap;
	}
	
	/**
	 * Returns the Robotium equivalent method for the givn androidMethod
	 * @param androidMethod
	 * @return
	 */
	public static String getRobotiumMethod(String androidMethod,String instanceOf){
		if(myMap.getInstance().getRobotiumMethodMap().containsKey(androidMethod))
			return myMap.getInstance().getRobotiumMethodMap().get(androidMethod).get(instanceOf);
		else
			return null;
	}	
}
