package generator;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

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
	private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/wikipedia-CrowdCrashGraph";	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/bites-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash1-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash2-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/opensudoku-CrowdCrashGraph";	
	private static String fileOutput="C:/Users/AnaGissel/Documents/MASTER/PFE/Workspace/TestAndroidCalculatorBlackBox2/src/com/testcalculator";
	
	
	public static void main(String[] args) {	
		//Neo4j Dijkstra query to obtain the crash scenario
		String query = "MATCH (from: APP), (to:CRASH_EVENT), "+
			      "paths = allShortestPaths( (from)-[:NEXT*]->(to) ) " +
			      "WITH REDUCE(dist = 0, rel in rels(paths) | dist + rel.pN) AS distance, paths "+
			      "RETURN paths, distance ORDER BY distance "+
			      "LIMIT 1";	
		
		if (args.length == 2) {
			DB_PATH = args[0].toString();
			fileOutput = args[1].toString();
		}	
		
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );		
		try ( Transaction ignored = db.beginTx();
			      Result result = db.execute(query) )
		{
			//get the trace for the database
			TraceFactory traceFactory = new TraceFactory();
			Trace trace = traceFactory.getTrace(result.columnAs("paths"));
			
			//Transform the android events
			EventTransformer generator = new EventTransformer();		
			List<String> robotiumMethods = generator.getRobotiumMethods(trace.getEvents());		
			
			//Generate the robotium test
			RobotiumTestClassGenerator clsGen = new RobotiumTestClassGenerator();
			clsGen.generateRobotiumTest(trace.getAppActivity(),robotiumMethods,fileOutput);
			db.shutdown();
		}									
	}
}
