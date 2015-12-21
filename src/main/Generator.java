package main;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import entities.Trace;
import factories.TraceFactory;
import generators.TestGenerator;

public class Generator {
	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/pockettool-CrowdCrashGraph";
	private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/wikipedia-CrowdCrashGraph";	
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/bites-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash1-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash2-CrowdCrashGraph";
	//private static String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/opensudoku-CrowdCrashGraph";	
	private static String fileOutput="C:/Users/AnaGissel/Documents/MASTER/PFE/Workspace/TestAndroidCalculatorBlackBox2/src/com/testcalculator";
	
	public static void main(String[] args) {	
		
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
			
			//Generate the robotium test
			TestGenerator generator = new TestGenerator(fileOutput);		
			generator.GenerateRobotiumTest(trace);									
		}									
	}
}
