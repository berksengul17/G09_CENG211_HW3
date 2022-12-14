package FurnitureFactory;

public class Wardrobe extends Furniture{

	private static final int INCOME_PERCENTAGE = 280;

	public Wardrobe(String code, String name) {
		super(code, name);
	}
	
	@Override
	public int calculateIncome() {
		return getCost() * INCOME_PERCENTAGE / 100;
	}
}
