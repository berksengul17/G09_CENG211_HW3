package FurnitureFactory;

public class Material {
	
	private String materialCode;
	private int length;
	private int width;
	private int height;
	private int cost;
	private int quality;
	
	
	public Material(String materialCode, int length, int width, int height, int cost, int quality) {
		this.materialCode = materialCode;
		this.length = length;
		this.width = width;
		this.height = height;
		this.cost = cost;
		this.quality = quality;
	}
	
	public Material() {
		this.materialCode = null;
		this.length = -1;
		this.width = -1;
		this.height = -1;
		this.cost = -1;
		this.quality = -1;
	}
	
	public Material(Material aMaterial) {
		this.materialCode = aMaterial.materialCode;
		this.length = aMaterial.length;
		this.width = aMaterial.width;
		this.height = aMaterial.height;
		this.cost = aMaterial.cost;
		this.quality = aMaterial.quality;
		
	}
	
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return String.format("Material code:%s "
				+ "Length:%d "
				+ "Width:%d "
				+ "Height:%d "
				+ "Cost:%d "
				+ "Quality:%d", materialCode, length, width, height, cost, quality);
	}
	
}
