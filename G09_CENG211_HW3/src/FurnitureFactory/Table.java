package FurnitureFactory;

public class Table extends Furniture{
	
	private static final int INCOME_PERCENTAGE = 300;

	public Table(String code, String name) {
		super(code, name);
	}
	
	@Override
	public int calculateIncome() {
		return getCost() * INCOME_PERCENTAGE / 100;
	}
}
