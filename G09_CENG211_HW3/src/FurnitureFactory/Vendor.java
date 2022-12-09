package FurnitureFactory;
import java.util.ArrayList;

public class Vendor {
	private ArrayList<Material> materialList;
	
	public Vendor() {
		this.materialList = null;
	}
	
	public Vendor(ArrayList<Material> theMaterialList) {
		this.materialList = theMaterialList;
	}
	
	public Vendor(Vendor aVendor) {
		this.materialList = aVendor.materialList;
	}
	
	public ArrayList<Material> getMaterialsList() {
		return this.materialList;
	}
}
