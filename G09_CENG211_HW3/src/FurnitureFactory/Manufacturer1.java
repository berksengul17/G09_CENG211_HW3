package FurnitureFactory;

import java.util.ArrayList;
import java.util.Stack;

import FileAccess.FileIO;

public class Manufacturer1 extends Manufacturer{

	private ArrayList<ArrayList<String>> furnitureList;
	private ArrayList<ArrayList<String>> materialList;
	
	public Manufacturer1() {
		super();
		this.furnitureList = FileIO.readFile("Manufacturer1Furnitures.csv");
		this.materialList = FileIO.readFile("Manufacturer1Materials.csv");
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
		
		super.setOwnedMaterialList(ownedMaterials);
	}
	
	
	public void produceFurnitures(int dayValue) {

		Stack<String[]> furnituresToProduce = initializeOrders(dayValue);
		
		while(!furnituresToProduce.isEmpty()) {
			String[] order = furnituresToProduce.peek();
			int amountToProduce = Integer.parseInt(order[1]);
			int amountProduced = 0;
			for(int i=0; i<amountToProduce; i++) {	
				Furniture producedFurniture = produce(order[0]);
				if(producedFurniture != null) {
					addToProducedFurnitures(producedFurniture);
					amountProduced++;
				}
			}
			
			order[1] = Integer.toString(amountToProduce-amountProduced);
			
			// If all the furnitures are produced pop the order
			if(Integer.parseInt(order[1]) == 0)
				furnituresToProduce.pop();
			else {
				addToUnproducedFurnitures(order);
				furnituresToProduce.pop();					
			}
			
		}
	}
	
	private Stack<String[]> initializeOrders(int dayValue){
		
		Stack<String[]> furnituresToProduce = new Stack<String[]>();
		ArrayList<String> orders = furnitureList.get(dayValue-1);
		
		// if there are unproduced furnitures add to the stack 
		for(String[] unProducedFurniture : getUnproducedFurnitures()) {
			furnituresToProduce.add(unProducedFurniture);
		}
		
		// clear the arraylist to store the current day's unproduced furnitures
		setUnproducedFurnitures(new ArrayList<String[]>());
		setProducedFurnitures(new ArrayList<Furniture>());
				
		// Start from 1 to skip day count
		for(int i=1; i<orders.size()-1; i+=2) {			
			String[] order = new String[2];
			order[0] = orders.get(i);
			order[1] = orders.get(i+1);
			furnituresToProduce.add(order);
		}
		
		return furnituresToProduce;
	}
	
	private Furniture produce(String order) {
		
		// dayValue-1 because index count starts from 0 
		FurnitureParts furniture = FurnitureParts.valueOf(order);
		
		ArrayList<String[]> parts = furniture.getParts();

		ArrayList<ArrayList<Material>> totalConsumedMaterials = new ArrayList<>();
		
		for(String[] part : parts) {
			String partCode = part[0];
			int amountNeeded = Integer.parseInt(part[1]);
			// LIFO
			for(ArrayList<Material> materials : getOwnedMaterials()) {
				if(materials.size() != 0) {
					Material material = materials.get(0);
					if(material.getCode().equals(partCode)) {
						ArrayList<Material> consumedMaterials = 
								consumeMaterial(materials, amountNeeded);
						if(consumedMaterials != null)
							totalConsumedMaterials.add(consumedMaterials);
						
						else return null;
					}
				}
			}
		}
		
		return createFurniture(furniture.getCode(), furniture.getName(), totalConsumedMaterials);
		
	}
}
