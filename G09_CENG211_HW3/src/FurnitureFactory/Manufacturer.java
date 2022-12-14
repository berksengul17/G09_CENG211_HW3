package FurnitureFactory;

import java.util.ArrayList;

import FileAccess.FileIO;

public class Manufacturer implements IManufacturer{
	
	private ArrayList<ArrayList<Material>> ownedMaterials;
	private ArrayList<Furniture> producedFurnitures;
	private ArrayList<String[]> unproducedFurnitures;
	
	public Manufacturer() {
		this.ownedMaterials = new ArrayList<ArrayList<Material>>();
		this.producedFurnitures = new ArrayList<Furniture>();
		this.unproducedFurnitures = new ArrayList<String[]>();
	}
	
	public ArrayList<ArrayList<Material>> getOwnedMaterials(){
		return new ArrayList<ArrayList<Material>>(ownedMaterials);
	}
	
	public void setOwnedMaterialList(ArrayList<ArrayList<Material>> materialList) {
		
		this.ownedMaterials = new ArrayList<ArrayList<Material>>(materialList);
	}
	
	public ArrayList<ArrayList<Furniture>> getGroupedProducedFurnitures(){
		
		int index = 0;
		
		ArrayList<String> groupNames = new ArrayList<String>();
		
		ArrayList<ArrayList<Furniture>> groupedProducedFurnitures = 
				new ArrayList<ArrayList<Furniture>>();
		
		while(index < producedFurnitures.size()) {
			
			ArrayList<Furniture> group = new ArrayList<>();
			group.add(producedFurnitures.get(index));
			
			if(!groupNames.contains(producedFurnitures.get(index).getName())) {
				for(int i=index+1; i<producedFurnitures.size(); i++) {
					Furniture temp = group.get(0);
					Furniture currFurniture = producedFurnitures.get(i);
					if(temp.getName().equals(currFurniture.getName())) {
						group.add(producedFurnitures.get(i));
					}
				}
				
				groupedProducedFurnitures.add(group);
				
				groupNames.add(producedFurnitures.get(index).getName());
			}
			
			
			index++;
			
		}
		
		return groupedProducedFurnitures;
	}
	
	public ArrayList<Furniture> getProducedFurnitures(){
		return new ArrayList<Furniture>(producedFurnitures);
	}
	
	public void setProducedFurnitures(ArrayList<Furniture> producedFurnitures) {
		this.producedFurnitures = new ArrayList<>(producedFurnitures);
	}
	
	public ArrayList<String[]> getUnproducedFurnitures(){
		return new ArrayList<String[]>(unproducedFurnitures);
	}
	
	public void setUnproducedFurnitures(ArrayList<String[]> unproducedFurnitures) {
		this.unproducedFurnitures = new ArrayList<>(unproducedFurnitures);	
	}
	
	public void addToProducedFurnitures(Furniture furniture) {
		this.producedFurnitures.add(furniture);
	}
	
	public void addToUnproducedFurnitures(String[] furniture) {
		this.unproducedFurnitures.add(furniture);
	}
	
	public ArrayList<Material> consumeMaterial(ArrayList<Material> materials, int amountNeeded) {
		
		ArrayList<Material> consumedMaterials = new ArrayList<Material>();
		
		if(materials.size() < amountNeeded) return consumedMaterials;
		
		else {
			int amountConsumed = 0;
			while(amountConsumed < amountNeeded) {
				// Remove from the last index
				Material material = materials.get(materials.size() - 1);
				materials.remove(material);
				consumedMaterials.add(material);
				amountConsumed++;
			}
			
			return consumedMaterials;
		}
		
	}
	
	
	public Furniture createFurniture(String furnitureCode, String furnitureName, 
			ArrayList<ArrayList<Material>> totalConsumedMaterials) {

		if(furnitureCode.startsWith("TB")) 
			return new Table(furnitureCode, furnitureName, totalConsumedMaterials);
		else if(furnitureCode.startsWith("WD")) 
			return new Wardrobe(furnitureCode, furnitureName, totalConsumedMaterials);
		else
			return new Shelf(furnitureCode, furnitureName, totalConsumedMaterials);
	
	}
	
}
