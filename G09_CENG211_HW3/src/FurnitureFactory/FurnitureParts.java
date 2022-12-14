package FurnitureFactory;

import java.util.ArrayList;

import FileAccess.FileIO;

public enum FurnitureParts {
	
	TB1401("TB1401", "Obsidian"),
	TB1402("TB1402", "Pearl"),
	TB1501("TB1501", "Elegant"),
	TB1502("TB1502", "Walnut"),
	TB1503("TB1503", "Garden"),
	WD2201("WD2201", "Lavinia"),
	WD2202("WD2202", "Loki"),
	WD2203("WD2203", "Atlas"),
	SH5001("SH5001", "Corner"),
	SH5002("SH5002", "Harmony"),
	SH5003("SH5003", "Luna"),
	SH5101("SH5101", "Hittite");
	
	ArrayList<String[]> parts;
	String materialCode;
	String furnitureName;
	
	FurnitureParts(String materialCode, String furnitureName) {
		this.materialCode = materialCode;
		this.furnitureName = furnitureName;
		this.parts = getFurniturePartsByCode(materialCode);
	}
	
	public String getCode() {
		return this.materialCode;
	}

	public String getName() {
		return this.furnitureName;
	}
	
	public ArrayList<String[]> getParts(){
		return new ArrayList<String[]>(parts);
	}
	

	ArrayList<String[]> getFurniturePartsByCode(String materialCode) {		
		ArrayList<ArrayList<String>> furnitureParts = FileIO.readFile("FurnitureParts.csv");
		ArrayList<String[]> blueprint = new ArrayList<String[]>();
		
		for(ArrayList<String> furniturePart : furnitureParts) {
			String currCode = furniturePart.get(0);
			for(int i=1; i<furniturePart.size()-1; i+=2) {
				if(materialCode.equals(currCode)) {
					String[] part = new String[2];
					part[0] = furniturePart.get(i);
					part[1] = furniturePart.get(i+1);
					blueprint.add(part);
				}
			}
		}
		
		return blueprint;
	}
	
	
}
