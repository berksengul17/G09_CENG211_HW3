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
		
		for(int i=1; i<4; i++) {
			Day day = new Day(i);
			day.simulateDay();
		}
	}
}
