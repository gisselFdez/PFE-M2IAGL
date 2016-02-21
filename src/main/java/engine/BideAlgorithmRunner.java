package engine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class contains the methods that runs the bide+ algorithm using the SPMF.jar
 * @author Ana Gissel
 *
 */
public class BideAlgorithmRunner {

	/**
	 * run the bide+ algortihm
	 */
	public String run(String bideInput){
		try {		
			//Bide output file path
			String bideOutput = System.getProperty("user.dir");
			if(bideOutput.contains("\\")){
				bideOutput = bideOutput+"\\output.txt";
			}
			else{
				bideOutput = bideOutput+"/output.txt";
			}	
		     

		      Process proc= Runtime.getRuntime().exec("java -jar "+getBideJarPath()+" run BIDE+_with_strings "+
						  			bideInput+" "+bideOutput+" 1");
		      return bideOutput;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}  
	}
	
	/**
	 * Get the path of the spmf jar containing the bide+ algorithm implementation
	 * @return
	 */
	private String getBideJarPath() {
	    String path = new File("jars/spmf.jar").getAbsolutePath();
	    return path;
	  }
}
