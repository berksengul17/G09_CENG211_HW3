package FurnitureFactory;

import java.util.ArrayList;

import FileAccess.FileIO;

public abstract class Furniture {
	
	private String code;
	private String name;
	private int cost;
	private int quality;
	private ArrayList<ArrayList<Material>> materialList;
	
	public Furniture() {
		this.code = "N/A";
		this.name = "N/A";
		this.materialList = new ArrayList<ArrayList<Material>>();
		this.cost = -1;
		this.quality = -1;
	}
	
	public Furniture(Furniture furniture) {
		this.code = furniture.code;
		this.name = furniture.name;
		this.materialList = new ArrayList<ArrayList<Material>>(furniture.materialList);
		this.cost = furniture.cost;
		this.quality = furniture.quality;
	}
	
	public Furniture(String code, String name, ArrayList<ArrayList<Material>> materialList) {
		this.code = code;
		this.name = name;
		this.materialList = new ArrayList<ArrayList<Material>>(materialList);
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
	
	public abstract int calculateIncome();
	
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
		
		int totalQuality = 0;
		int totalVolume = 0;

		for(ArrayList<Material> materials : materialList) {
			for(Material material : materials) {
				int quality = material.getQuality();
				int volume = material.getHeight() * material.getLength() * material.getWidth();
				totalVolume += volume;
				totalQuality += quality * volume;
				
			}
		}
		
		return totalQuality / totalVolume;
	}
	
}
