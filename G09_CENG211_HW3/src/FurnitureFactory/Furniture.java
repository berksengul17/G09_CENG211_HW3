package FurnitureFactory;

public abstract class Furniture {
	
	private String furnitureCode;
	private int cost;
	private int quality;
	//private int income;
	
	public Furniture(String furnitureCode, int cost, int quality) {
		this.furnitureCode = furnitureCode;
		this.cost = cost;
		this.quality = quality;
	}
}
