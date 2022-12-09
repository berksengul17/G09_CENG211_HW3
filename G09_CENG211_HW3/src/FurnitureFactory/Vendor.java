package FurnitureFactory;
import java.util.ArrayList;

import FileAccess.FileIO;

public class Vendor {
	
	private ArrayList<ArrayList<String>> materialList;
	
	public Vendor() {
		materialList = new ArrayList<ArrayList<String>>(FileIO.readFile("VendorPossessions.csv"));
	}
	
	public ArrayList<ArrayList<String>> getMaterialList(){
		return new ArrayList<ArrayList<String>>(materialList);
	}
}
