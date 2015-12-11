package Main;
import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import Generator.TestGenerator;

public class Main {
	
	//private static final String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/pockettool-CrowdCrashGraph";
	//private static final String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/wikipedia-CrowdCrashGraph";	
	//private static final String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/bites-CrowdCrashGraph";
	//private static final String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash1-CrowdCrashGraph";
	//private static final String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/google-crash2-CrowdCrashGraph";
	private static final String DB_PATH = "C:/Users/AnaGissel/Documents/Neo4j/opensudoku-CrowdCrashGraph";
	
	public static void main(String[] args) {
		String query = "MATCH (from: APP), (to:CRASH_EVENT), "+
			      "paths = allShortestPaths( (from)-[:NEXT*]->(to) ) " +
			      "WITH REDUCE(dist = 0, rel in rels(paths) | dist + rel.pN) AS distance, paths "+
			      "RETURN paths, distance ORDER BY distance "+
			      "LIMIT 1";
				
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );		
		
		//Execute query
		try ( Transaction ignored = db.beginTx();
			      Result result = db.execute(query) )
		{				
			//generate test
			TestGenerator generator = new TestGenerator();		
			generator.GenerateRobotiumTest(result.columnAs("paths"));									
		}
	}
}
