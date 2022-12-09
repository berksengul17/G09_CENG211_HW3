package FurnitureFactory;

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

	public abstract int calculateIncome();
	
}
