package engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import core.ExceptionNode;
import core.StaticContextNode;

/**
 * This class contains the methods to generate the textual specification file
 * @author Ana Gissel
 *
 */
public class TextualFileGenerator {

	public void generateFile(List<String> actions, String fileOutput,ExceptionNode exception){
				
		File file = new File(fileOutput+"/TextualSpecification.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//describe context
			List<StaticContextNode> staticContexts = exception.getContexts();
			String sdk="";
			String manufacturer="";
			for(StaticContextNode context: staticContexts){
				if(sdk.equals("")){
					sdk = context.getAndroidSDK();
					manufacturer = context.getManufacturer();
				}
				else
				{
					sdk = sdk+", "+context.getAndroidSDK();
					manufacturer = manufacturer+", "+context.getManufacturer();
				}				
			}
			bw.write("Crash context:");
			bw.newLine();
			bw.write("  AndroidSDK = "+sdk);
			bw.newLine();
			bw.write("  Manufacturer= "+manufacturer);
			bw.newLine();
			bw.newLine();
			bw.write("Steps to reproduce the crash:");			
			bw.newLine();
			
			//add user actions
			for(String action: actions){
				if(!action.equals("")){
					bw.write("  - "+action);
					bw.newLine();
				}
			}
			//add exception detail
			bw.write("  - "+exception.getException());
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
