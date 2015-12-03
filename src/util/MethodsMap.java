package util;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MethodsMap {

	private static MethodsMap myMap = new MethodsMap();	
	private static HashMap<String, HashMap<String, Method>> methodMap;

	private MethodsMap (){		
		try {
			//---Initialize all the values for the MAP
			methodMap = new HashMap<String, HashMap<String, Method>>();			
			Class<?>[] paramTypes = {HashMap.class};
			
			//OnClick	
			HashMap<String, Method> instancesOnClick = new HashMap<String, Method>();
			instancesOnClick.put("Button", MethodFactory.class.getDeclaredMethod("buttonOnClick",paramTypes));
			instancesOnClick.put("ImageView", MethodFactory.class.getDeclaredMethod("imageViewOnClick",paramTypes));
			
			methodMap.put("onClick", instancesOnClick);
			
			//performItemClick			
			HashMap<String, Method> instancesPerformClick = new HashMap<String, Method>();
			instancesPerformClick.put("ListMenuItemView", MethodFactory.class.getDeclaredMethod("listMenuPerformItemClick",paramTypes));
			
			methodMap.put("performItemClick", instancesPerformClick);
			
		} catch (NoSuchMethodException| SecurityException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the actual instance of MethodsMap
	 * @return
	 */
	public MethodsMap getInstance(){
		return myMap;
	}
	
	/**
	 * Returns the MethodMap
	 * @return
	 */
	private HashMap<String, HashMap<String, Method>> getMethodMap(){
		return methodMap;
	}
	
	/**
	 * 
	 * @param androidMethod
	 * @return
	 */
	public static Method getMethod(String androidMethod, String instanceOf){
		if(myMap.getInstance().getMethodMap().containsKey(androidMethod))
			return myMap.getInstance().getMethodMap().get(androidMethod).get(instanceOf);
		else
			return null;
	}	
}
