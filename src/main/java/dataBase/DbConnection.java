package dataBase;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import entities.Trace;
import factories.TraceFactory;

/**
 * This class contains all the methods to connect to a Neo4j database, execute a query and close the database connection.
 * @author Ana Gissel
 *
 */
public class DbConnection {
	
	private static String DB_PATH = "";
	private static GraphDatabaseService db;
	//Neo4j Dijkstra query to obtain the crash scenario
	private static String query = "MATCH (from: APP), (to:CRASH_EVENT), "+
				      "paths = allShortestPaths( (from)-[:NEXT*]->(to) ) " +
				      "WITH REDUCE(dist = 0, rel in rels(paths) | dist + rel.pN) AS distance, paths "+
				      "RETURN paths, distance ORDER BY distance "+
				      "LIMIT 1";

	/**
	 * Make a connection with the database located at "path", executes the disjtra query and
	 * call the corresponding methods to obtain a Trace object from the result of the query.
	 * @param path - Neo4j database location
	 * @return The trace object.
	 */
	public static Trace getTraceFromDB(String path){
		DB_PATH = path;
				
		db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );	
		System.out.println("before conexion");
		try ( Transaction ignored = db.beginTx();
			      Result result = db.execute(query) )
		{
			TraceFactory traceFactory = new TraceFactory();
			//get the trace for the database		
			Trace trace = traceFactory.getTrace(result.columnAs("paths"));
			return trace;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Close the connection with the database.
	 */
	public static void closeDb(){
		db.shutdown();
	}
}
