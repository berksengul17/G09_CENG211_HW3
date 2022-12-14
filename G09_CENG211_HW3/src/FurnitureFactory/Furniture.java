package FurnitureFactory;

import java.util.ArrayList;

import FileAccess.FileIO;

public abstract class Furniture {
	
	private String code;
	private String name;
	private int cost;
	private int quality;
	
	public Furniture() {
		this.code = "N/A";
		this.name = "N/A";
		this.cost = -1;
		this.quality = -1;
	}
	
	public Furniture(Furniture furniture) {
		this.code = furniture.code;
		this.name = furniture.name;
		this.cost = furniture.cost;
		this.quality = furniture.quality;
	}
	
	public Furniture(String code, String name) {
		this.code = code;
		this.name = name;
		this.cost = calculateCost();
		this.quality = calculateQuality();
	}
	
	public String getFurnitureCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getQuality() {
		return quality;
	}
	
	private int calculateCost() {
		ArrayList<String[]> parts = FurnitureParts.valueOf(code).getParts();
		
		int totalCost = 0;
		for(String[] part : parts) {
			Material material = FileIO.getMaterialByCode(part[0]);
			int amount = Integer.parseInt(part[1]);
			totalCost += material.getCost() * amount;;
		}
		
		return totalCost;
		
	}

	private int calculateQuality() {
		ArrayList<String[]> parts = FurnitureParts.valueOf(code).getParts();
		
		int sum = 0;
		int totalVolume = 0;
		for(String[] part : parts) {
			Material material = FileIO.getMaterialByCode(part[0]);
			int volume = Integer.parseInt(part[1]);
			
			sum += material.getQuality() * volume;
			totalVolume += volume;
		}
		
		return sum / totalVolume;
	}
	
	public abstract int calculateIncome();
	
	
}
