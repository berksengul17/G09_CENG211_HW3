package FurnitureFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Day {
	
	private int dayValue;
	private Vendor vendor;
	private Manufacturer1 manufacturer1;
	private Manufacturer2 manufacturer2;
	private NumberFormat formatter;
	
	public Day(int dayValue) {
		this.dayValue = dayValue;
		this.vendor = new Vendor();
		this.manufacturer1 = new Manufacturer1();
		this.manufacturer2 = new Manufacturer2();
		this.formatter = NumberFormat.getCurrencyInstance(Locale.US);
	}
	
	public void simulateDay() {
		
		manufacturer1.buyMaterials(vendor, dayValue);
		manufacturer1.produceFurnitures(dayValue);
		
		manufacturer2.buyMaterials(vendor, dayValue);
		manufacturer2.produceFurnitures(dayValue);
		
		System.out.println("Day" + dayValue + ":");
		System.out.println("Manufacturer1:");
		
		ArrayList<ArrayList<Furniture>> manufacturer1FurnituresProduced = 
				manufacturer1.getGroupedProducedFurnitures();
		
		ArrayList<String[]> manufacturer1FurnituresUnproduced =
				manufacturer1.getUnproducedFurnitures();
		
		printOutput(manufacturer1FurnituresProduced, manufacturer1FurnituresUnproduced);
		
		System.out.println("Manufacturer2:");
				
		ArrayList<ArrayList<Furniture>> manufacturer2FurnituresProduced = 
				manufacturer2.getGroupedProducedFurnitures();
		
		ArrayList<String[]> manufacturer2FurnituresUnproduced =
				manufacturer2.getUnproducedFurnitures();
		
		printOutput(manufacturer2FurnituresProduced, manufacturer2FurnituresUnproduced);
		
	}
	
	private void printOutput(ArrayList<ArrayList<Furniture>> producedFurnitures,
							ArrayList<String[]> unproducedFurnitures) {
		
		for(ArrayList<Furniture> furnitureGroup : producedFurnitures) {
			
			String furnitureName = furnitureGroup.get(0).getName();
			System.out.print(furnitureName + ":\t");
			
			int income = getFurnitureGroupIncome(furnitureGroup);
			int[] qualityCount = getQualityCount(furnitureGroup);
			
			int badQltCount = qualityCount[0];
			int normalQltCount = qualityCount[1];
			int goodQltCount = qualityCount[2];
			int veryGoodQltCount = qualityCount[3];
			int perfectQltCount = qualityCount[4];
			
			System.out.printf("Bad Qlt: %d, Normal Qlt: %d, Good Qlt: %d, "
					+ "Very Good Qlt: %d, Perfect Qlt: %d, Earning: %s", 
					badQltCount, normalQltCount, goodQltCount,
					veryGoodQltCount, perfectQltCount, formatter.format(income));
			
			System.out.println();
		}
		
		printTotalExpenseAndIncome(producedFurnitures);
		printUnproducedFurnitures(unproducedFurnitures);

		
	}	
	
	private void printTotalExpenseAndIncome(ArrayList<ArrayList<Furniture>> producedFurnitures) {
		
		int totalExpense = 0, totalIncome = 0;
		
		for(ArrayList<Furniture> furnitureGroup : producedFurnitures) {
			for(Furniture furniture : furnitureGroup) {
				totalExpense += furniture.getCost();
				totalIncome += furniture.calculateIncome();
			}
		}
		
		System.out.println("Total Expense: " + formatter.format(totalExpense));
		System.out.println("TotalIncome: " + formatter.format(totalIncome));
	}
	
	private void printUnproducedFurnitures(ArrayList<String[]> unproducedFurnitures) {
		
		System.out.println("Unproduced Furnitures:");
		
		if(unproducedFurnitures.size() == 0) System.out.println("All orders are produced");
		
		else {
			for(int index=0; index<unproducedFurnitures.size(); index++) {
				String[] unproducedFurniture = unproducedFurnitures.get(index);
				int amountUnproduced = Integer.parseInt(unproducedFurniture[1]);
				String furnitureName = unproducedFurniture[0];
				System.out.print(amountUnproduced + " " + furnitureName);
				if(index != unproducedFurnitures.size()-1) System.out.print(", ");
				else System.out.println();
			}
		}
	}
	
	private int[] getQualityCount(ArrayList<Furniture> furnitureGroup){
		
		int[] qualityCount = {0, 0, 0, 0, 0};

		for(Furniture furniture : furnitureGroup) {
			
			int quality = furniture.getQuality();

			if(quality < 92)
				qualityCount[0]++;
			else if(quality >= 94 && quality >= 92)
				qualityCount[1]++;
			else if(quality <= 96 && quality >= 94)
				qualityCount[2]++;
			else if(quality <= 98 && quality >= 96)
				qualityCount[3]++;
			else
				qualityCount[4]++;

		}
		
		return qualityCount;
		
	}
	
	private int getFurnitureGroupIncome(ArrayList<Furniture> furnitureGroup) {
		
		int income = 0;
		
		for(Furniture furniture : furnitureGroup) {
			income += furniture.calculateIncome();
		}
		
		return income;
	}

}
