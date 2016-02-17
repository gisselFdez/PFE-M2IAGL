package generator;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import core.Graph;
import dataBase.DbConnection;
import engine.EventSpecification;
import engine.EventTransformer;
import engine.RobotiumTestClassGenerator;
import engine.TextualFileGenerator;
import factories.TraceFactory;

/**
 * Generator 
 * @author Ana Gissel
 * 
 */
public class Generator {
	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/pockettool-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/wikipedia-CrowdCrashGraph";	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/bites-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash1-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash2-CrowdCrashGraph";
	private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/opensudoku-CrowdCrashGraph";	
	private static String fileOutput="C:/Users/AnaGissel/Documents/MASTER/PFE/Workspace/TestAndroidCalculatorBlackBox2/src/com/testcalculator";
	private static String textualOutput="C:/Users/AnaGissel/Desktop";
		
	public static void main(String[] args) {		
		if (args.length == 2) {
			DB_PATH = args[0].toString();
			fileOutput = args[1].toString();
			textualOutput = args[1].toString();
		}			
		if (args.length == 3) {
			DB_PATH = args[0].toString();
			fileOutput = args[1].toString();
			textualOutput = args[2].toString();
		}
		    Graph trace  = DbConnection.getTraceFromDB(DB_PATH);
			DbConnection.closeDb();
			
		    Generator generator = new Generator();
									
			//generate the test			
			generator.generateTest(trace);
			
			//generate natural language specification
			generator.generateSpecification(trace);														
	}
	
	/**
	 * Call all the corresponding methods to generate a Robotium blackbox test from the given trace
	 * @param trace
	 */
	private void generateTest(Graph trace){		
		EventTransformer generator = new EventTransformer();
		RobotiumTestClassGenerator clsGen = new RobotiumTestClassGenerator();
		
		//Transform the android events				
		List<String> robotiumMethods = generator.getRobotiumMethods(trace.getEvents());		
		
		//Generate the robotium test		
		clsGen.generateRobotiumTest(trace.getAppActivity().getActivity(),robotiumMethods,fileOutput);
	}
	
	/**
	 * Call all the corresponding methods to generate the test specification in natural language from the given trace
	 * @param trace
	 */
	private void generateSpecification(Graph trace){
		EventSpecification specification = new EventSpecification();
		TextualFileGenerator fileGenerator = new TextualFileGenerator();
		
		//Transform the android events				
		List<String> textualSpecification = specification.getTextualSpecification(trace.getEvents());
		fileGenerator.generateFile(textualSpecification, textualOutput);
	}
}

