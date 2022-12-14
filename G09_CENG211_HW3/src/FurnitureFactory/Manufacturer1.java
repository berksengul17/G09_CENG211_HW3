package FurnitureFactory;

import java.util.ArrayList;
import java.util.Stack;

import FileAccess.FileIO;

public class Manufacturer1 extends Manufacturer{

	private ArrayList<ArrayList<String>> furnitureList;
	private ArrayList<ArrayList<String>> materialList;
	private ArrayList<ArrayList<Material>> ownedMaterials;
	private ArrayList<Furniture> producedFurnitures;
	private ArrayList<String[]> unproducedFurnitures;
	
	public Manufacturer1() {
		this.furnitureList = FileIO.readFile("Manufacturer1Furnitures.csv");
		this.materialList = FileIO.readFile("Manufacturer1Materials.csv");
		this.ownedMaterials = new ArrayList<ArrayList<Material>>();
		this.producedFurnitures = new ArrayList<Furniture>();
		this.unproducedFurnitures = new ArrayList<String[]>();
	}
	
	
	public void setOwnedMaterialList(ArrayList<ArrayList<Material>> materialList) {
		
		this.ownedMaterials = new ArrayList<ArrayList<Material>>(materialList);
	}
	
	public ArrayList<ArrayList<Furniture>> getProducedFurnitures(){
		
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
		
		
		/*ArrayList<ArrayList<Furniture>> groupedProducedFurnitures = 
				new ArrayList<ArrayList<Furniture>>();
		
		for(Furniture furniture : producedFurnitures) {
			for(ArrayList<Furniture> group : groupedProducedFurnitures) {
				String groupName = group.get(0).getName();
				if(furniture.getName().equals(groupName)) {
					group.add(furniture);
				} else {
					ArrayList<Furniture> newGroup = new ArrayList<Furniture>();
					newGroup.add(furniture);
					groupedProducedFurnitures.add(newGroup);
				}
			}
		}
		
		return groupedProducedFurnitures;*/
	}
	
	public ArrayList<String[]> getUnproducedFurnitures(){
		return new ArrayList<String[]>(unproducedFurnitures);
	}

	public void buyMaterials(Vendor vendor, int dayValue) {
		
		ArrayList<ArrayList<Material>> ownedMaterials = new ArrayList<ArrayList<Material>>();
		ArrayList<String> materialOrder = materialList.get(dayValue-1);
			
		for(int j=1; j<materialOrder.size(); j+=2) {
			String material = materialOrder.get(j);
			int amount = Integer.parseInt(materialOrder.get(j+1));
			
			ArrayList<Material> order = vendor.buy(material, amount); 
			ownedMaterials.add(order);
		}
		
		setOwnedMaterialList(ownedMaterials);
	}
	
	
	public void produceFurnitures(int dayValue) {

		Stack<String[]> furnituresToProduce = initializeOrders(dayValue);
		
		while(!furnituresToProduce.isEmpty()) {
			String[] order = furnituresToProduce.peek();
			int amountToProduce = Integer.parseInt(order[1]);
			int amountProduced = 0;
			for(int i=0; i<amountToProduce; i++) {	
				Furniture producedFurniture = produce(order[0], dayValue);
				if(producedFurniture != null) {
					producedFurnitures.add(producedFurniture);
					amountProduced++;
				}
			}
			
			order[1] = Integer.toString(amountToProduce-amountProduced);
			
			// If all the furnitures are produced pop the order
			if(Integer.parseInt(order[1]) == 0)
				furnituresToProduce.pop();
			else {
				unproducedFurnitures.add(order);
				furnituresToProduce.pop();					
			}
			
		}
	}
	
	private Stack<String[]> initializeOrders(int dayValue){
		
		Stack<String[]> furnituresToProduce = new Stack<String[]>();
		ArrayList<String> orders = furnitureList.get(dayValue-1);
		
		// if there are unproduced furnitures add to the stack 
		for(String[] unProducedFurniture : unproducedFurnitures) {
			furnituresToProduce.add(unProducedFurniture);
		}
		
		// clear the arraylist to store the current day's unproduced furnitures
		unproducedFurnitures.clear();
		producedFurnitures.clear();
		
		// Start from 1 to skip day count
		for(int i=1; i<orders.size()-1; i+=2) {			
			String[] order = new String[2];
			order[0] = orders.get(i);
			order[1] = orders.get(i+1);
			furnituresToProduce.add(order);
		}
		
		return furnituresToProduce;
	}
	
	private Furniture produce(String order, int dayValue) {
		
		// dayValue-1 because index count starts from 0 
		FurnitureParts furniture = FurnitureParts.valueOf(order);
		
		ArrayList<String[]> parts = furniture.getParts();

		for(String[] part : parts) {
			String partCode = part[0];
			int amountNeeded = Integer.parseInt(part[1]);
			// LIFO
			for(ArrayList<Material> materials : ownedMaterials) {
				if(materials.size() != 0) {
					Material material = materials.get(0);
					if(material.getCode().equals(partCode)) {
						if(!consumeMaterial(materials, amountNeeded))
							return null;
					}
				}
			}
		}
		
		return createFurniture(furniture.getCode(), furniture.getName());
		
	}
	
	private boolean consumeMaterial(ArrayList<Material> materials, int amountNeeded) {
		
		if(materials.size() < amountNeeded) return false;
		
		else {
			int amountConsumed = 0;
			while(amountConsumed < amountNeeded) {
				// Remove from the last index
				materials.remove(materials.size()-1);
				amountConsumed++;
			}
			
			return true;
		}
		
	}
	
	private Furniture createFurniture(String furnitureCode, String furnitureName) {
		
		if(furnitureCode.startsWith("TB")) 
			return new Table(furnitureCode, furnitureName);
		else if(furnitureCode.startsWith("WD")) 
			return new Wardrobe(furnitureCode, furnitureName);
		else
			return new Shelf(furnitureCode, furnitureName);
	}
}
