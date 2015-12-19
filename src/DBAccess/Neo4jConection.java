package DBAccess;

import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import Generator.TestGenerator;

public class Neo4jConection {

	String query = "MATCH (from: APP), (to:CRASH_EVENT), "+
		      "paths = allShortestPaths( (from)-[:NEXT*]->(to) ) " +
		      "WITH REDUCE(dist = 0, rel in rels(paths) | dist + rel.pN) AS distance, paths "+
		      "RETURN paths, distance ORDER BY distance "+
		      "LIMIT 1";		
	
	public ResourceIterator<Path> executeQueryToDB(String DB_PATH){
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		System.out.println("excecute query");
		//Execute query
		try ( Transaction ignored = db.beginTx();
			      Result result = db.execute(query) )
		{
			return result.columnAs("paths");									
		}
	}
	
}
