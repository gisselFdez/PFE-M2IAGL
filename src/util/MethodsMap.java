package util;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * this class contains all the mappings between Robotium and Android methods
 * @author Ana Gissel
 */
public class MethodsMap {

	private static MethodsMap myMap = new MethodsMap();	
	private static HashMap<String, HashMap<String, Method>> methodMap;

	/**
	 * Initialize the mappings with the equivalences between Android and Robotium methods  
	 */
	private MethodsMap (){		
		try {
			//---Initialize all the values for the MAP
			methodMap = new HashMap<String, HashMap<String, Method>>();			
			Class<?>[] paramTypes = {HashMap.class};
			
			//-------------- OnClick	
			HashMap<String, Method> instancesOnClick = new HashMap<String, Method>();
			instancesOnClick.put("Button", MethodFactory.class.getDeclaredMethod("clickOnButton",paramTypes));
			instancesOnClick.put("ImageView", MethodFactory.class.getDeclaredMethod("clickOnView",paramTypes));
			instancesOnClick.put("FrameLayout", MethodFactory.class.getDeclaredMethod("clickOnView",paramTypes));
			instancesOnClick.put("ActionMenuItemView", MethodFactory.class.getDeclaredMethod("clickOnText",paramTypes));
			methodMap.put("onClick", instancesOnClick);
			
			//-------------- performItemClick			
			HashMap<String, Method> instancesPerformClick = new HashMap<String, Method>();
			instancesPerformClick.put("ListMenuItemView", MethodFactory.class.getDeclaredMethod("clickInList",paramTypes));			
			methodMap.put("performItemClick", instancesPerformClick);
			
			//-------------- onMenuItemSelected
			HashMap<String, Method> instancesMenuItemSelected = new HashMap<String, Method>();
			instancesMenuItemSelected.put("MenuItemImpl", MethodFactory.class.getDeclaredMethod("clickOnText",paramTypes));			
			methodMap.put("onMenuItemSelected", instancesMenuItemSelected);
			
			//-------------- performLongPress
			HashMap<String, Method> instancesPerformLongPress = new HashMap<String, Method>();
			instancesPerformLongPress.put("LinearLayout", MethodFactory.class.getDeclaredMethod("clickLongOnView",paramTypes));
			instancesPerformLongPress.put("RelativeLayout", MethodFactory.class.getDeclaredMethod("clickLongOnView",paramTypes));
			instancesPerformLongPress.put("AbsListView", MethodFactory.class.getDeclaredMethod("clickLongInList",paramTypes));	
			methodMap.put("performLongPress", instancesPerformLongPress);
			
			//-------------- onKeyUp
			HashMap<String, Method> instancesKeyUp = new HashMap<String, Method>();
			instancesKeyUp.put("KeyEvent", MethodFactory.class.getDeclaredMethod("sendKey",paramTypes));			
			methodMap.put("onKeyUp", instancesKeyUp);
			
			//-------------- onConfigurationChanged
			HashMap<String, Method> instancesonConfigurationChanged = new HashMap<String, Method>();
			instancesonConfigurationChanged.put("Configuration", MethodFactory.class.getDeclaredMethod("setActivityOrientation",paramTypes));			
			methodMap.put("onConfigurationChanged", instancesonConfigurationChanged);
			
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
