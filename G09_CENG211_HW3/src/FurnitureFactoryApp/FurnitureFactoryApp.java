package FurnitureFactoryApp;

import FurnitureFactory.Day;

public class FurnitureFactoryApp {
	public static void main(String[] args) {
		
		for(int i=1; i<4; i++) {
			Day day = new Day(i);
			day.simulateDay();
		}
	}
}
