package FurnitureFactory;

public class Material {
	
	private String materialCode;
	private int length;
	private int width;
	private int height;
	private int cost;
	
	
	public Material(String materialCode, int length, int width, int height, int cost) {
		this.materialCode = materialCode;
		this.length = length;
		this.width = width;
		this.height = height;
		this.cost = cost;
	}
	
	public Material() {
		this.materialCode = null;
		this.length = -1;
		this.width = -1;
		this.height = -1;
		this.cost = -1;
	}
	
	public Material(Material copy) {
		this.materialCode = copy.materialCode;
		this.length = copy.length;
		this.width = copy.width;
		this.height = copy.height;
		this.cost = copy.cost;
		
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

	
	
	
}
