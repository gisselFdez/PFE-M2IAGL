package engine;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import factories.RobotiumMethodFactory;

public class RobotiumTestClassGeneratorTest {

	private static File outputFilePath;
	
	@BeforeClass 
	public static void setup() {
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
		RobotiumTestClassGenerator generator = new RobotiumTestClassGenerator();
		generator.generateRobotiumTest("activityApp", Arrays.asList("clickOnButton(\"OK\")"), outputFilePath.toString());
		
		File file = new File(outputFilePath.toString(),"TestRobotium.java" );
		assertTrue(file.exists());		
	}	
	
}
