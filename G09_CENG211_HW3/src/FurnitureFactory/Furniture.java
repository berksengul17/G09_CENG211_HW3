package FurnitureFactory;

import java.util.ArrayList;

public abstract class Furniture {
	
	private String furnitureCode;
	private int cost;
	private int quality;
	
	public Furniture() {
		this.furnitureCode = "N/A";
		this.cost = -1;
		this.quality = -1;
	}
	
	public Furniture(Furniture furniture) {
		this.furnitureCode = furniture.furnitureCode;
		this.cost = furniture.cost;
		this.quality = furniture.quality;
	}
	
	public Furniture(String furnitureCode, int cost, int quality) {
		this.furnitureCode = furnitureCode;
		this.cost = cost;
		this.quality = quality;
	}
	
	public String getFurnitureCode() {
		return furnitureCode;
	}

	public int getCost() {
		return cost;
	}

	public int getQuality() {
		return quality;
	}
	
	public int calculateCost() {
		ArrayList<String> parts = FurnitureParts.valueOf(furnitureCode).getParts();
		
		int cost = 0;
		for(int i=0; i<parts.size()-1; i++) {
			
		}
		
		
	}

	public abstract int calculateIncome();
	
	public abstract int calculateQuality();
	
}
