package FurnitureFactory;

public class Shelf extends Furniture{

	private static final int INCOME_PERCENTAGE = 320;
	
	public Shelf(String code, String name) {
		super(code, name);
	}
	
	@Override
	public int calculateIncome() {
		return getCost() * INCOME_PERCENTAGE / 100;
	}
}
