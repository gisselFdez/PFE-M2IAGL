package factories;

import java.util.HashMap;

/**
 * Contains all the methods that construct the Robotium events code
 * @author Ana Gissel
 *
 */
public class RobotiumMethodFactory {

	/**
	 * Returns the corresponding Robotium code for a clickOnButton method
	 * @param methodParametersMap
	 * @return 
	 */
	public String clickOnButton(HashMap<String, String> methodParametersMap){
		//generate: clickOnButton("Text");
		String text = methodParametersMap.get("mText");
		return "clickOnButton("+text+")";
	}
	
	/**
	 * Returns the corresponding Robotium code for a clickOnView method
	 * @param methodParametersMap
	 * @return
	 */
	public String clickOnView(HashMap<String, String> methodParametersMap){
		//generate: clickOnView(solo.getView(view))
		String view = methodParametersMap.get("mID");
		return "clickOnView(solo.getView("+view+"))";
	}
	
	/**
	 * Returns the corresponding Robotium code for a clickInList method
	 * @param methodParametersMap
	 * @return
	 */
	public String clickInList(HashMap<String, String> methodParametersMap){
		//generate: pressMenuItem(index);
		String valueId =  methodParametersMap.get("id");
		return "clickInList("+valueId+")";
	}
	
	/**
	 * Returns the corresponding Robotium code for a clickOnText method
	 * @param methodParametersMap
	 * @return
	 */
	public String clickOnText(HashMap<String, String> methodParametersMap){
		//generate: clickOnText(string);
		String text =  methodParametersMap.get("mTitle");
		return "clickOnText("+text+")";
	}
	
	/**
	 * Returns the corresponding Robotium code for a clickLongOnView method
	 * @param methodParametersMap
	 * @return
	 */
	public String clickLongOnView(HashMap<String, String> methodParametersMap){
		//generate: clickLongOnView(solo.getView(view));
		String view =  methodParametersMap.get("mID");
		return "clickLongOnView(solo.getView("+view+"))";
	}
	
	/**
	 * Returns the corresponding Robotium code for a sendKey method
	 * @param methodParametersMap
	 * @return
	 */
	public String sendKey(HashMap<String, String> methodParametersMap){
		//generate: sendKey();
		String key =  methodParametersMap.get("keyCode");
		return "sendKey("+key+")";
	}
	
	/**
	 * Returns the corresponding Robotium code for a clickLongInList method
	 * @param methodParametersMap
	 * @return
	 */
	public String clickLongInList(HashMap<String, String> methodParametersMap){
		//generate: clickLongInList(line)
		String line = methodParametersMap.get("position");
		return "clickLongInList("+line+")";
	}
	
	/**
	 * Returns the corresponding Robotium code for a setActivityOrientation method
	 * @param methodParametersMap
	 * @return
	 */
	public String setActivityOrientation(HashMap<String, String> methodParametersMap){
		//generate: setActivityOrientation(1)		
		return "setActivityOrientation(1)";
	}
}
