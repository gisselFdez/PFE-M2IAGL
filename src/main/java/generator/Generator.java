package generator;

import core.Graph;
import dataBase.DbConnection;
import engine.EventSpecification;
import engine.EventTransformer;

/**
 * Generator 
 * @author Ana Gissel
 * 
 */
public class Generator {
	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/pockettool-CrowdCrashGraph";
	private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/wikipedia-CrowdCrashGraph";	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/bites-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash1-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash2-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/opensudoku-CrowdCrashGraph";	
	public static String fileOutput="C:/Users/AnaGissel/Documents/MASTER/PFE/Workspace/TestAndroidCalculatorBlackBox2/src/com/testcalculator";
	public static String textualOutput="C:/Users/AnaGissel/Desktop";
		
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
		    Graph graph  = DbConnection.getTraceFromDB(DB_PATH);
			DbConnection.closeDb();
												
			//generate the test			
		    graph.accept(new EventTransformer());			
			//generate natural language specification
		    graph.accept(new EventSpecification());
		    System.out.println("DONE");
	}	
}

