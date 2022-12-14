package FurnitureFactory;

import java.util.ArrayList;

public class Table extends Furniture{
	
	private static final int INCOME_PERCENTAGE = 300;

	public Table(String code, String name, ArrayList<ArrayList<Material>> materialList) {
		super(code, name, materialList);
	}
	
	@Override
	public int calculateIncome() {
		return getCost() * INCOME_PERCENTAGE / 100;
	}
}
