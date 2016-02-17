package engine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import core.EventNode;

public class EventSpecificationTest {

	@Test
	public void test() {
		//Test data
		List<EventNode> androidEvents = new ArrayList<EventNode>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("mText", "\"OK\"");
		map.put("action", "onClick");
		map.put("instanceOf", "Button");
		androidEvents.add(new EventNode(map));
		
		//expected result
		List<String> expected = Arrays.asList("Click on button \"OK\".");
		
		//Tested class
		EventSpecification event = new EventSpecification();
		List<String> specifications = event.getTextualSpecification(androidEvents);
		
		assertTrue("Expected 'robotiumEvents' and 'expected' to be equal."+
	            "\n  'robotiumEvents' = "+specifications+
	            "\n  'expected' = "+expected, 
	            expected.equals(specifications));
	}

}
