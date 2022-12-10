package FurnitureFactory;

import java.util.ArrayList;

import FileAccess.FileIO;

public enum FurnitureParts {
	
	TB1401("TB1401"),
	TB1402("TB1402"),
	TB1501("TB1501"),
	TB1502("TB1502"),
	TB1503("TB1503"),
	WD2201("WD2201"),
	WD2202("WD2202"),
	WD2203("WD2203"),
	SH5001("SH5001"),
	SH5002("SH5002"),
	SH5003("SH5003"),
	SH5101("SH5101");
	
	ArrayList<String> parts;
	
	FurnitureParts(String materialCode) {
		parts = getFurniturePartsByCode(materialCode);
	}
	
	public ArrayList<String> getParts(){
		return new ArrayList<String>(parts);
	}

	ArrayList<String> getFurniturePartsByCode(String materialCode) {		
		ArrayList<ArrayList<String>> furnitureParts = FileIO.readFile("FurnitureParts.csv");
		ArrayList<String> blueprint = null;
		
		for(ArrayList<String> furniturePart : furnitureParts) {
			String currCode = furniturePart.get(0);
			if(materialCode.equals(currCode)) {
				furniturePart.remove(currCode);
				blueprint = furniturePart;
			}
		}
		
		return blueprint;
	}
	
	
}
