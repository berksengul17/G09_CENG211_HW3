package FurnitureFactory;

import java.util.ArrayList;

public interface IManufacturer {

	public ArrayList<ArrayList<Material>> getOwnedMaterials();
	public void setOwnedMaterialList(ArrayList<ArrayList<Material>> materialList);
	public ArrayList<ArrayList<Furniture>> getGroupedProducedFurnitures();
	public ArrayList<Furniture> getProducedFurnitures();
	public void setProducedFurnitures(ArrayList<Furniture> producedFurnitures);
	public ArrayList<String[]> getUnproducedFurnitures();
	public void setUnproducedFurnitures(ArrayList<String[]> unproducedFurnitures);
	public void addToProducedFurnitures(Furniture furniture);
	public void addToUnproducedFurnitures(String[] furniture);
	public ArrayList<Material> consumeMaterial(ArrayList<Material> materials, int amountNeeded);
	public Furniture createFurniture(String furnitureCode, String furnitureName, 
			ArrayList<ArrayList<Material>> totalConsumedMaterials);
	
}
