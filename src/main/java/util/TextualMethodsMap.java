package util;

import java.lang.reflect.Method;
import java.util.HashMap;

import factories.TextualMethodFactory;


/**
 * this class contains all the mappings between the textual generator methods and Android methods
 * @author Ana Gissel
 *
 */
public class TextualMethodsMap {

	private static TextualMethodsMap myMap = new TextualMethodsMap();	
	private static HashMap<String, HashMap<String, Method>> methodMap;
	
	/**
	 * Initialize the mappings with the equivalences between Android and Textual methods  
	 */
	private TextualMethodsMap (){		
		try {
			//Initialize all the values for the MAP
			methodMap = new HashMap<String, HashMap<String, Method>>();			
			Class<?>[] paramTypes = {HashMap.class};
			
			//OnClick events	
			HashMap<String, Method> instancesOnClick = new HashMap<String, Method>();
			instancesOnClick.put("Button", TextualMethodFactory.class.getDeclaredMethod("clickOnButton",paramTypes));
			instancesOnClick.put("ImageView", TextualMethodFactory.class.getDeclaredMethod("clickOnView",paramTypes));
			instancesOnClick.put("FrameLayout", TextualMethodFactory.class.getDeclaredMethod("clickOnView",paramTypes));
			instancesOnClick.put("ActionMenuItemView", TextualMethodFactory.class.getDeclaredMethod("clickOnText",paramTypes));
			methodMap.put("onClick", instancesOnClick);
			
			//performItemClick events		
			HashMap<String, Method> instancesPerformClick = new HashMap<String, Method>();
			instancesPerformClick.put("ListMenuItemView", TextualMethodFactory.class.getDeclaredMethod("clickInList",paramTypes));			
			methodMap.put("performItemClick", instancesPerformClick);
			
			//onMenuItemSelected events
			HashMap<String, Method> instancesMenuItemSelected = new HashMap<String, Method>();
			instancesMenuItemSelected.put("MenuItemImpl", TextualMethodFactory.class.getDeclaredMethod("clickOnText",paramTypes));			
			methodMap.put("onMenuItemSelected", instancesMenuItemSelected);
			
			//performLongPress events
			HashMap<String, Method> instancesPerformLongPress = new HashMap<String, Method>();
			instancesPerformLongPress.put("LinearLayout", TextualMethodFactory.class.getDeclaredMethod("clickLongOnView",paramTypes));
			instancesPerformLongPress.put("RelativeLayout", TextualMethodFactory.class.getDeclaredMethod("clickLongOnView",paramTypes));
			methodMap.put("performLongPress", instancesPerformLongPress);
			
			//onKeyUp events
			HashMap<String, Method> instancesKeyUp = new HashMap<String, Method>();
			instancesKeyUp.put("KeyEvent", TextualMethodFactory.class.getDeclaredMethod("keyEvent",paramTypes));			
			methodMap.put("onKeyUp", instancesKeyUp);
			
			//onConfigurationChanged events
			HashMap<String, Method> instancesonConfigurationChanged = new HashMap<String, Method>();
			instancesonConfigurationChanged.put("Configuration", TextualMethodFactory.class.getDeclaredMethod("setActivityOrientation",paramTypes));			
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
	public TextualMethodsMap getInstance(){
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
