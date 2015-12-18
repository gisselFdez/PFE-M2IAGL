package Generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.robotium.solo.Solo;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

import android.test.ActivityInstrumentationTestCase2;

import com.squareup.javapoet.TypeSpec;

public class RobotiumTestCodeGenerator {	
	
	/**
	 * Generates the Robotium code (BlackboxTest) for the given userActions in the especified output
	 * @param packageApp
	 * @param userActions
	 * @param output
	 */
	public void generateRobotiumTest(String packageApp, List<String> userActions,String output){
				
		//Generate the class
		TypeSpec testRobotium = TypeSpec.classBuilder("TestRobotium")
			.superclass(ActivityInstrumentationTestCase2.class)
		    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)			    
		    .addField(Class.class, "launcherActivityClass", Modifier.PRIVATE, Modifier.STATIC)
		    .addField(FieldSpec.builder(String.class, "LAUNCHER_ACTIVITY_FULL_CLASSNAME")
		            .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
		            .initializer("$S", packageApp)
		            .build())			    
		    .addStaticBlock(CodeBlock.builder()	
		    		.beginControlFlow("try")
		    	    .addStatement("launcherActivityClass = $N ","Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME)")	
		    	    .endControlFlow()
		    	    .beginControlFlow("catch(ClassNotFoundException e)")
		    	    .addStatement("throw new RuntimeException(e)")
		    	    .endControlFlow()
		    		.build())			    
		    .addMethod(generateConstructor())
		    .addField(Solo.class,"solo",Modifier.PRIVATE)
		    .addMethod(generateSetUpMethod())
		    .addMethod(generateTestMethod(userActions))
		    .addMethod(generateTearDownMethod())
		    .build();

		JavaFile javaFile = JavaFile.builder("", testRobotium)
		    .build();

		try {
			javaFile.writeTo(new File(output));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate the code from the Robotium TearDown method
	 * @return
	 */
	private MethodSpec generateTearDownMethod(){
		return MethodSpec.methodBuilder("tearDown")
			    .addAnnotation(Override.class)
			    .addModifiers(Modifier.PUBLIC)
			    .addException(Exception.class)
			    .addStatement("solo.finishOpenedActivities()")
			    .addStatement("super.tearDown()")
			    .build();
	}
	
	/**
	 * Generate the code for the Robotium method testDisplayBlackBox containing all the userActions 
	 * @param userActions
	 * @return
	 */
	private MethodSpec generateTestMethod(List<String> userActions){
		Builder builder = MethodSpec.methodBuilder("testDisplayBlackBox");
		builder.addModifiers(Modifier.PUBLIC);
		builder.addStatement("solo.waitForActivity($N)", "LAUNCHER_ACTIVITY_FULL_CLASSNAME");
		
		for(String action: userActions){
			if(!action.equals(""))
			{
				builder.addStatement("solo.$N",action);
				builder.addStatement("solo.$N", getWaitTime());
			}			
		}			    
		return builder.build();
	}
	
	/**
	 * Generate the code from the Robotium setUp method
	 * @return
	 */
	private MethodSpec generateSetUpMethod(){
		return MethodSpec.methodBuilder("setUp")
			    .addAnnotation(Override.class)
			    .addModifiers(Modifier.PROTECTED)
			    .addException(Exception.class)
			    .addStatement("solo = new Solo(getInstrumentation(),getActivity())")
			    .addStatement("super.setUp()")
			    .build();
	}
	
	/**
	 * Generate the constructor for a Blackbox Robotium test class
	 * @return
	 */
	private MethodSpec generateConstructor(){		
		return MethodSpec.constructorBuilder()
			    .addModifiers(Modifier.PUBLIC)
			    .addException(ClassNotFoundException.class)
			    .addStatement("super($N)","launcherActivityClass")
			    .build();
	}	
	
	/**
	 * Define the sleep time between each Robotium action
	 * @return
	 */
	private String getWaitTime(){
		int time = 1000;
		return "sleep("+time+")";
	}
}
