package factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;

import entities.Trace;

/**
 * Contains all the methods that treat the DB information to transform it into a Trace object
 * @author Ana Gissel
 *
 */
public class TraceFactory {

	/**
	 * Treat the DB information to transform it into a Trace object
	 * @param n_column
	 * @return
	 */
	public Trace getTrace(Iterator<Path> n_column){
		Trace trace = new Trace();
		List<String> actions = new ArrayList<String>();
		
		//Generate code for every node
		while(n_column.hasNext()){
			Path path = n_column.next();
			
			//Get all Nodes from path
			Iterable<Node> nodes = path.nodes();
			for (Node node : nodes)
    		{
				for (String key : node.getPropertyKeys()) {
					if(key.equals("app"))
						trace.setAppActivity(node.getProperty(key).toString());					
					if(!key.equals("app") && !key.equals("exception") && !key.equals("counter"))
						actions.add(node.getProperty(key).toString());					
					if(key.equals("exception"))
						trace.setException(node.getProperty(key).toString());
            	}
    		}					
        }
		trace.setActions(actions);
		return trace;
	}
}
