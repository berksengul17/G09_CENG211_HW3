package FileAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import FurnitureFactory.Material;

public class FileIO {
	
	/**
	 * Read the file in the given path
	 * @return ArrayList<ArrayList<String>> containing the read lines
	 * 			the inner ArrayList holds the data for each line and 
	 * 			the outer ArrayList holds these lines.
	 */
	public static ArrayList<ArrayList<String>> readFile(String filePath){
		
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			String line;
			while((line = br.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				ArrayList<String> currLine = new ArrayList<String>();
				while(tokenizer.hasMoreTokens()) {
					currLine.add(tokenizer.nextToken());
				}
				lines.add(currLine);
			}
			
			br.close(); 
			
		} catch(FileNotFoundException e) {
			System.err.format("Given file does not exist: %s", filePath);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}
		
	public static Material getMaterialByCode(String materialcode) {
		
		ArrayList<ArrayList<String>> properties = readFile("RawMaterialProperties.csv");
		
		Material desiredMaterial = null;
		
		for(ArrayList<String> materialInfo : properties ) {
			if(materialcode.equals(materialInfo.get(0))) {
				
				String code = materialInfo.get(0);
				int length = Integer.parseInt(materialInfo.get(1));
				int width = Integer.parseInt(materialInfo.get(2));
				int height = Integer.parseInt(materialInfo.get(3));
				int cost = Integer.parseInt(materialInfo.get(4));
				
				desiredMaterial = new Material(code, length, width, height, cost);
			}
			
		}
		
		return desiredMaterial;

	}
}
 