package engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class contains the methods to generate the textual specification file
 * @author Ana Gissel
 *
 */
public class TextualFileGenerator {

	public void generateFile(List<String> actions, String fileOutput){
		
		String content = "Steps to reproduce the crash:";
		File file = new File(fileOutput+"/TextualSpecification.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
			for(String action: actions){
				if(!action.equals("")){
					bw.write("- "+action);
					bw.newLine();
				}
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
