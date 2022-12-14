package FurnitureFactoryApp;

import FurnitureFactory.Day;

public class FurnitureFactoryApp {
	public static void main(String[] args) {
		
		/*
		Vendor vendor = new Vendor();
		
		ArrayList<Material> materialList = vendor.getMaterialList();
		
		
		for(Material material : materialList) {
			System.out.println(material);
		}
		
		System.out.println(materialList.size());*/
		
		Day day = new Day(1);
		day.simulateDay();
	}
}
