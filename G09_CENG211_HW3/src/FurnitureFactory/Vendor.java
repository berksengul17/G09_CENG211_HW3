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
		
		for(ArrayList<String> possession : vendorPossessions) {
			Material material = FileIO.getMaterialByCode(possession.get(0));
			material.setQuality(Integer.parseInt(possession.get(1)));
			materialList.add(material);		
		}
		
		return materialList;
	}
	
	public ArrayList<Material> getMaterialList(){
		return new ArrayList<Material>(materialList);
	}
	
	public ArrayList<Material> buy(String materialCode, int amount) {
		
		ArrayList<Material> copyMaterialList = new ArrayList<>(materialList);
		ArrayList<Material> order = new ArrayList<Material>();
		
		int amountBuyed = 0;
		for(Material material : copyMaterialList) {
			if(material.getCode().equals(materialCode) && amountBuyed < amount) {
				materialList.remove(material);
				order.add(new Material(material));
				amountBuyed++;
			}
		}
		
		return order;
	}
	
}
