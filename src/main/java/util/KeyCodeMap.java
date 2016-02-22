package util;
import java.util.HashMap;
/**
 * Class containing the list of equivalences for every key pressed in an Android environement.
 * @author Ana Gissel
 *
 */
public class KeyCodeMap {

	private static KeyCodeMap myMap = new KeyCodeMap();	
	private static HashMap<Integer, String> keyCodeMap;
	
	private KeyCodeMap(){
		keyCodeMap = new HashMap<Integer, String>();
		//a - z-> 29 - 54
		keyCodeMap.put(29, "a");
		keyCodeMap.put(30, "b");
		keyCodeMap.put(31, "c");
		keyCodeMap.put(32, "d");
		keyCodeMap.put(33, "e");
		keyCodeMap.put(34, "f");
		keyCodeMap.put(35, "g");
		keyCodeMap.put(36, "h");
		keyCodeMap.put(37, "i");
		keyCodeMap.put(38, "j");
		keyCodeMap.put(39, "k");
		keyCodeMap.put(40, "l");
		keyCodeMap.put(41, "m");
		keyCodeMap.put(42, "n");
		keyCodeMap.put(43, "o");
		keyCodeMap.put(44, "p");
		keyCodeMap.put(45, "q");
		keyCodeMap.put(46, "r");
		keyCodeMap.put(47, "s");
		keyCodeMap.put(48, "t");
		keyCodeMap.put(49, "u");
		keyCodeMap.put(50, "v");
		keyCodeMap.put(51, "w");
		keyCodeMap.put(52, "x");
		keyCodeMap.put(53, "y");
		keyCodeMap.put(54, "z");
		
		//"0" - "9"-> 7 - 16
		keyCodeMap.put(7, "0");
		keyCodeMap.put(8, "1");
		keyCodeMap.put(9, "2");
		keyCodeMap.put(10, "3");
		keyCodeMap.put(11, "4");
		keyCodeMap.put(12, "5");
		keyCodeMap.put(13, "6");
		keyCodeMap.put(14, "7");
		keyCodeMap.put(15, "8");
		keyCodeMap.put(16, "9");
		
		//BACK BUTTON - 4, MENU BUTTON - 82
		keyCodeMap.put(4, "Back Button");
		keyCodeMap.put(82, "Menu Button");
		//UP-19, DOWN-20, LEFT-21, RIGHT-22
		keyCodeMap.put(19, "Up");
		keyCodeMap.put(20, "Down");
		keyCodeMap.put(21, "Left");
		keyCodeMap.put(22, "Right");
		//SELECT (MIDDLE) BUTTON - 23
		keyCodeMap.put(23, "Select Button");
		//SPACE - 62, SHIFT - 59, ENTER - 66, BACKSPACE - 67
		keyCodeMap.put(62, "Space");
		keyCodeMap.put(59, "Shift");
		keyCodeMap.put(66, "Enter");
		keyCodeMap.put(67, "BackSpace");
	}
	
	/**
	 * Returns the actual instance of KeyCodeMap
	 * @return
	 */
	public KeyCodeMap getInstance(){
		return myMap;
	}
	
	/**
	 * Returns the KeyCodeMap
	 * @return
	 */
	private HashMap<Integer, String> getKeyCodeMap(){
		return keyCodeMap;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public static String getKeyValue(int code){
		if(myMap.getInstance().getKeyCodeMap().containsKey(code))
			return myMap.getInstance().getKeyCodeMap().get(code);
		else
			return null;
	}	
}
