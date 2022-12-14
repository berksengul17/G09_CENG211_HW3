package FurnitureFactory;

import java.util.ArrayList;

public class Shelf extends Furniture{

	private static final int INCOME_PERCENTAGE = 320;
	
	public Shelf(String code, String name, ArrayList<ArrayList<Material>> materialList) {
		super(code, name, materialList);
	}
	
	@Override
	public int calculateIncome() {
		return getCost() * INCOME_PERCENTAGE / 100;
	}
}
