package engine;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class reads the output file from the bide+ algorithm and translate
 * @author Ana Gissel
 *
 */
public class BideFileReader {

	/**
	 * Read the result of the BIDE+ algorithm placed on the outputfile
	 * @param file
	 * @return
	 */
	public String read(String file){
		String result="";
		try {
			if(file.equals(""))
				return result;
			else{
				Scanner scan = new Scanner(new File(file));
			    while(scan.hasNextLine()){
			        String line = transformLine(scan.nextLine());
			        if(!line.equals("") && !line.equals(" ")){
			        	if(result.equals(""))
				        	result = "("+line+")";
				        else
				        	result = result+", ("+line+")";
				        System.out.println(line);
			        }			        
			    }
				return result;
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Clean the line of the bide output file
	 * @param line
	 * @return
	 */
	private String transformLine(String line){
		String result="";
		//remove #SUP : ....
		int indx=line.indexOf("#");
		if(indx!=-1){
			line = line.substring(0,indx);
		}	
		//remove blank spaces at the end of the line
		while(line.endsWith(" ")){
			line = line.substring(0,line.length()-1);
		}
		
		//remove "-1"
		String[] elements=line.split("-1");
		for(String element: elements){
			if(!element.equals("") && !element.equals(" ")){
				if(result.equals(""))
					result = element;
				else
					result = result+", "+element;	
			}								
		}
		return result;
	}
}
