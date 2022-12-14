package FurnitureFactory;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import FileAccess.FileIO;

public class Manufacturer2 extends Manufacturer{

	private Queue<String> furnituresToBeProduced;
	private Stack<String> materialsToBeUsed;
	
	public Manufacturer2() {
		ArrayList<String> day1Furnitures = FileIO.readFile("Manufacturer2Furnitures.csv").get(0);
		ArrayList<String> day1Materials= FileIO.readFile("Manufacturer2Materials.csv").get(0);
		
		furnituresToBeProduced = new PriorityQueue<>(day1Furnitures);
		
	}
}
