package factories;

import java.util.HashMap;

import util.KeyCodeMap;

/**
 * Contains all the methods that generates the textual description of an android event
 * @author Ana Gissel
 *
 */
public class TextualMethodFactory {

	/**
	 * Generates the corresponding textual description for a clickOnButton android method
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String clickOnButton(HashMap<String, String> methodParametersMap){
		String text = methodParametersMap.get("mText");
		return "Click on button \""+text+"\".";
	}
	
	/**
	 * Generates the corresponding textual description for a clickOnView android method
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String clickOnView(HashMap<String, String> methodParametersMap){
		//generate: clickOnView(solo.getView(view))
		String view = methodParametersMap.get("mID");
		return "Click on view id: \""+view+"\".";
	}
	
	/**
	 * Generates the corresponding textual description for a clickInList android method
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String clickInList(HashMap<String, String> methodParametersMap){
		//generate: pressMenuItem(index);
		String valueId =  methodParametersMap.get("id");
		return "Click on item #\""+valueId+"\" from list.";
	}
	
	/**
	 * Generates the corresponding textual description for a keyEvent android method
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String keyEvent(HashMap<String, String> methodParametersMap){
		//generate: return "Click on "x.key" key";
		String key =  methodParametersMap.get("keyCode");
		try{
			return "Click on \""+KeyCodeMap.getKeyValue(Integer.parseInt(key))+"\" key.";
		}
		catch(NumberFormatException e){
			return "";
		}		
	}
	
	/**
	 * Generates the corresponding textual description for a click on text android method
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String clickOnText(HashMap<String, String> methodParametersMap){
		//generate: clickOnText(string);
		String text =  methodParametersMap.get("mTitle");
		return "Click on text \""+text+"\".";
	}
	
	/**
	 * Generates the corresponding textual description for a long click on view android method
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String clickLongOnView(HashMap<String, String> methodParametersMap){
		//generate: clickLongOnView(solo.getView(view));
		String view =  methodParametersMap.get("mID");
		return "Long click on view id:"+view+".";
	}
	
	/**
	 * Generates the corresponding textual description for changing the activity orientation
	 * @param methodParametersMap
	 * @return The textual description of the event
	 */
	public String setActivityOrientation(HashMap<String, String> methodParametersMap){	
		return "Change activity orientation.";
	}
}
