package FurnitureFactoryApp;

import java.util.ArrayList;

import FurnitureFactory.Material;
import FurnitureFactory.Vendor;

public class FurnitureFactoryApp {
	public static void main(String[] args) {
		
		Vendor vendor = new Vendor();
		
		ArrayList<Material> materialList = vendor.getMaterialList();
		
		
		for(Material material : materialList) {
			System.out.println(material);
		}
		
		System.out.println(materialList.size());
	}
}
