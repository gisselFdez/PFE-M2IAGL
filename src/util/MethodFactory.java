package util;

import java.util.HashMap;

public class MethodFactory {

	/**
	 * Returns the corresponding Robotium code for the OnClick Action to a Button
	 * @param methodParametersMap
	 * @return
	 */
	public String buttonOnClick(HashMap<String, String> methodParametersMap){
		//generate: clickOnButton("Text");
		String method = MethodsRobotiumMap.getRobotiumMethod(methodParametersMap.get("action"),
				methodParametersMap.get("instanceOf"));
		String text = methodParametersMap.get("mText");
		return method+"("+text+")";
	}
	
	public String imageViewOnClick(HashMap<String, String> methodParametersMap){
		//generate: clickOnView(solo.getView(view))
		String method = MethodsRobotiumMap.getRobotiumMethod(methodParametersMap.get("action"),
				methodParametersMap.get("instanceOf"));
		String view = methodParametersMap.get("mID");
		return method+"(solo.getView("+view+"))";
	}
	
	public String listMenuPerformItemClick(HashMap<String, String> methodParametersMap){
		//generate: pressMenuItem(index);
		String method = MethodsRobotiumMap.getRobotiumMethod(methodParametersMap.get("action"),
				methodParametersMap.get("instanceOf"));
		String valueId =  methodParametersMap.get("id");
		return method+"("+valueId+")";
	}
}
