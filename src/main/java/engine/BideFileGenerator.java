package engine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import core.DynamicContextNode;
import core.EventNode;
import core.ExceptionNode;

/**
 * This class generates a file with format compatible with the input format of BIDE+ algorithm.
 * @author Ana Gissel
 *
 */
public class BideFileGenerator {

	/**
	 * Generate a file containing the dynamic context of every node (with a format compatible with the input format
	 * of BIDE+ algorithm.
	 * @param events
	 */
	public void generate(List<EventNode> events,ExceptionNode exception){		
		List<List<String>> contextsList = generateContextsList(events,exception);
		String matrix[][] = createContextMatrix(contextsList);	
				
		//generate file
		String path = createOutputFile();
		for(int i=0;i<matrix.length;i++){
			String line="";
			for(int j=0; j< matrix[i].length;j++){
				if(j==matrix[i].length-1 && matrix[i][j]!=null){
					line = line+matrix[i][j]+" -1 -2";
				}
				else if(matrix[i][j]!=null)
					line = line+matrix[i][j]+" -1 ";
			}
			if(line.endsWith("-1 ")){
				line = line+"-2";//line.substring(0, line.length()-3)+"-2";
			}
				
			//add line to file
			writeToOutputFile(path,line);
		}		
	}
	
	/**
	 * Creates the bide file
	 * @return
	 */
	private static String createOutputFile(){
		String path = System.getProperty("user.dir");
		if(path.contains("\\")){
			path = path+"\\bideInput.txt";
		}
		else{
			path = path+"/bideInput.txt";
		}
		
		try (
				Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(path), "utf-8"))) {
	   writer.write("");
	   writer.close();
	   return path;
	} catch ( IOException e) {
		e.printStackTrace();
		return "";
	}
		
	}
	/**
	 * Adds the specified text to the bide input file
	 * @param text
	 */
	private static void writeToOutputFile(String path,String text){
		
		try(PrintWriter output = new PrintWriter(new FileWriter(path,true))) 
		{
		    output.printf("%s\r\n", text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sort ant transform the list of dynamic contexts into a matrix of strings.
	 * @param contextsList
	 * @return
	 */
	private String[][] createContextMatrix(List<List<String>> contextsList){		
		int maxLength = getMaximalContextLegth(contextsList);		
		String[][] matrix = new String[maxLength][contextsList.size()];
		
		for(int i=0;i<contextsList.size();i++){
			List<String> contexts = contextsList.get(i);
			for(int j=0;j<maxLength;j++){
				if(j<contexts.size()){
					matrix[j][i]=contexts.get(j);
				}
			}
		}		
		return matrix;
	}
	
	/**
	 * Generates a lists with all the dynamic contexts of the crash scenario (User events & exception)
	 * @param events
	 * @param exception
	 * @return
	 */
	private List<List<String>> generateContextsList(List<EventNode> events,ExceptionNode exception){
		List<List<String>> contextsList = new ArrayList<List<String>>();
		
		//Get every context node for each user event
		for(EventNode event : events){
			List<String> contexts = new ArrayList<String>();
			
			for(DynamicContextNode dynContex :event.getDynamicContext()){
				String context = "netType="+dynContex.getNetworkType()+" network="+dynContex.getNetwork()+" gps="+dynContex.getGps();
				contexts.add(context);				
			}
			contextsList.add(contexts);
		}
		
		//Get every context node for the exception node
		List<String> contexts = new ArrayList<String>();
		for(DynamicContextNode dynContex: exception.getDynamicContexts()){
			String context = "netType="+dynContex.getNetworkType()+" network="+dynContex.getNetwork()+" gps="+dynContex.getGps();
			contexts.add(context);	
		}
		contextsList.add(contexts);
		return contextsList;
	}
	
	/**
	 * Calculates the longest context list
	 * @param contextsList
	 * @return
	 */
	private int getMaximalContextLegth(List<List<String>> contextsList){
		int max =0;
		for(List<String> contexts : contextsList){
			if(contexts.size()>max)
				max = contexts.size();
		}
		
		return max;
	}
}
