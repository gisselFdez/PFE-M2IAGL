package util;

import java.util.HashMap;

public class MethodFactory {

	/**
	 * Returns the corresponding Robotium code for the OnClick Action to a Button
	 * @param methodParametersMap
	 * @return
	 */
	public String clickOnButton(HashMap<String, String> methodParametersMap){
		//generate: clickOnButton("Text");
		String text = methodParametersMap.get("mText");
		return "clickOnButton("+text+")";
	}
	
	public String clickOnView(HashMap<String, String> methodParametersMap){
		//generate: clickOnView(solo.getView(view))
		String view = methodParametersMap.get("mID");
		return "clickOnView(solo.getView("+view+"))";
	}
	
	public String clickInList(HashMap<String, String> methodParametersMap){
		//generate: pressMenuItem(index);
		String valueId =  methodParametersMap.get("id");
		return "clickInList("+valueId+")";
	}
	
	public String clickOnText(HashMap<String, String> methodParametersMap){
		//generate: clickOnText(string);
		String text =  methodParametersMap.get("mTitle");
		return "clickOnText("+text+")";
	}
	
	public String clickLongOnView(HashMap<String, String> methodParametersMap){
		//generate: clickLongOnView(solo.getView(view));
		String view =  methodParametersMap.get("mID");
		return "clickLongOnView(solo.getView("+view+"))";
	}
	
	public String sendKey(HashMap<String, String> methodParametersMap){
		//generate: sendKey();
		String key =  methodParametersMap.get("keyCode");
		return "sendKey("+key+")";
	}
	
}
