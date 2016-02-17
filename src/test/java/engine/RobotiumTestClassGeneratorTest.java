package engine;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RobotiumTestClassGeneratorTest {

	private static File outputFilePath;
	private static RobotiumTestClassGenerator generator;
	
	@BeforeClass 
	public static void setup() {
		generator = new RobotiumTestClassGenerator();
		outputFilePath = new File("/tmp");
	      if(!outputFilePath.exists()){
	    	  outputFilePath.mkdir();
	      }
	}

	@AfterClass 
	public static void teardown() {
	      if(outputFilePath.exists()){
	    	  outputFilePath.delete();
	      }
	}
	
	@Test
	public void testGenerateRobotiumTest() {		
		generator.generateRobotiumTest("activityApp", Arrays.asList("clickOnButton(\"OK\")"), outputFilePath.toString());
		
		File file = new File(outputFilePath.toString(),"TestRobotium.java" );
		assertTrue(file.exists());		
	}	
	
}
