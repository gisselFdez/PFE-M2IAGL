package factories;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.test.TestGraphDatabaseFactory;

public class TraceFactoryTest {

	GraphDatabaseService graphDb;
	
	@Before
	public void prepareTestDatabase()
	{
	    graphDb = new TestGraphDatabaseFactory().newImpermanentDatabase();
	}
	
	@After
	public void destroyTestDatabase()
	{
	    graphDb.shutdown();
	}
	
	@Test
	public void testGetTrace() {
		try ( Transaction tx = graphDb.beginTx() )
		{
		    // Database operations go here
			Node firstNode = graphDb.createNode();
			firstNode.setProperty( "app", "AppActivity" );
			Node secondNode = graphDb.createNode();
			secondNode.setProperty( "message", "World!" );
			Node thirdNode = graphDb.createNode();
			thirdNode.setProperty( "exception", "Exception" );
			
		    tx.success();
		}		
	}

}
