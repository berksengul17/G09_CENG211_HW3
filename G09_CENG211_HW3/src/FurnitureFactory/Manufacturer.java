package FurnitureFactory;

import java.util.ArrayList;

import FileAccess.FileIO;

public abstract class Manufacturer {
	
	private ArrayList<ArrayList<String>> furnitureParts;
	
	public Manufacturer() {
		furnitureParts = FileIO.readFile("FurnitureParts.csv");
	}
}
