package FurnitureFactory;
import java.util.ArrayList;

import FileAccess.FileIO;

public class Vendor {
	
	private ArrayList<Material> materialList;
	
	public Vendor() {
		this.materialList = createMaterialList();
	}
	
	public ArrayList<Material> createMaterialList(){
		ArrayList<Material> materialList = new ArrayList<Material>();
		
		ArrayList<ArrayList<String>> vendorPossessions = 
				new ArrayList<ArrayList<String>>(FileIO.readFile("VendorPossessions.csv"));
		
		ArrayList<ArrayList<String>> materialProperties = 
				new ArrayList<ArrayList<String>>(FileIO.readFile("RawMaterialProperties.csv"));
		
		for(ArrayList<String> possession : vendorPossessions) {
			String materialCode = possession.get(0);
			int quality = Integer.parseInt(possession.get(1));
			for(ArrayList<String> property : materialProperties) {
				int length = Integer.parseInt(property.get(1));
				int width = Integer.parseInt(property.get(2));
				int height = Integer.parseInt(property.get(3));
				int cost = Integer.parseInt(property.get(4));			
				materialList.add(new Material(materialCode, length, width, height, cost, quality));
			}			
		}
		
		return materialList;
	}
}
