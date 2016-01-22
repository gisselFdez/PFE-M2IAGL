package generator;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import dataBase.DbConnection;
import engine.EventTransformer;
import engine.RobotiumTestClassGenerator;
import entities.Trace;
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
	private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash1-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash2-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/opensudoku-CrowdCrashGraph";	
	private static String fileOutput="C:/Users/AnaGissel/Documents/MASTER/PFE/Workspace/TestAndroidCalculatorBlackBox2/src/com/testcalculator";
		
	public static void main(String[] args) {		
		if (args.length == 2) {
			DB_PATH = args[0].toString();
			fileOutput = args[1].toString();
		}			
		    Trace trace  = DbConnection.getTraceFromDB(DB_PATH);
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
	private void generateTest(Trace trace){		
		EventTransformer generator = new EventTransformer();
		RobotiumTestClassGenerator clsGen = new RobotiumTestClassGenerator();
		
		//Transform the android events				
		List<String> robotiumMethods = generator.getRobotiumMethods(trace.getEvents());		
		
		//Generate the robotium test		
		clsGen.generateRobotiumTest(trace.getAppActivity(),robotiumMethods,fileOutput);
	}
	
	/**
	 * Call all the corresponding methods to generate the test specification in natural language from the given trace
	 * @param trace
	 */
	private void generateSpecification(Trace trace){
		
	}
}

