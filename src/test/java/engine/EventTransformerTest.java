package engine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class EventTransformerTest {

	@Test
	public void testGetRobotiumMethods() {		
		//Test data
		List<HashMap<String,String>> androidEvents = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mText", "\"OK\"");
		map.put("action", "onClick");
		map.put("instanceOf", "Button");
		androidEvents.add(map);
		
		//expected result
		List<String> expected = Arrays.asList("clickOnButton(\"OK\")");
		
		//Tested class
		EventTransformer event = new EventTransformer();
		List<String> robotiumEvents = event.getRobotiumMethods(androidEvents);
		
		assertTrue("Expected 'robotiumEvents' and 'expected' to be equal."+
	            "\n  'robotiumEvents' = "+robotiumEvents+
	            "\n  'expected' = "+expected, 
	            expected.equals(robotiumEvents));
	}
}
