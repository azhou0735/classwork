package arrays;

import java.util.Arrays;

public class ArraysMain {

	private String[] testArray;
	private int[] intRay;
	
	public ArraysMain() {
		intRay = new int[100];
		//populate(intRay);
		//checkOccurences(intRay,3,18);
		//Arrays is a Utility class included in Java for formatting input
		populate1ToN(intRay);
		//swap(intRay, 0, 1);
		shuffle(intRay);
		for(int i = 0; i < intRay.length/2; i ++)
		{
			swap(intRay,i,intRay.length-i-1);
		}
		System.out.println(Arrays.toString(intRay));
	}

	private void shuffle(int[] intRay) {
		for(int i = 0; i < intRay.length; i ++)
		{
			//swap(intRay, i , (int)(Math.random() * intRay.length) + 0);
			swap(intRay, (int)(Math.random() * intRay.length) + 0 , (int)(Math.random() * intRay.length) + 0);
		}
	}

	private void swap(int[] intRay, int i, int j) {
		int temp = intRay[i];
		intRay[i] = intRay[j];
		intRay[j] = temp;
	}

	private void populate1ToN(int[] intRay) {
		for(int i = 0; i < intRay.length; i++)
		{
			intRay[i] = i+1;
		}
	}

	private void checkOccurences(int[] arr, int start, int end) {
		int[] counter = new int[end-start+1];
		for(int value:arr)
		{
			counter[value-start] ++;
		}
		for(int i = 0; i < counter.length; i ++)
		{
			System.out.println("A "+(start+i)+" was rolled "+counter[i]+" times.");
		}
	}

	public void populate(int[] intRay){
		for(int i = 0; i < intRay.length; i++) 
		{
			intRay[i] = diceRoll(3);
		}
		/*int i=0;
		for(int value:intRay)
		{
			intRay[i]=diceRoll(2);
			i++;
		}*/
		
	}
	
	public void notes() {
		//1.collection of primitives or objects
		//SPECIAL NOTE: ONLY collection of primitives
		
		//2.Size cannot be modified
		
		//3.Elements of an array are REFERENCES
		//Changing an element of an array doesn't change the object
		
		//Two types of constructors, first type:
		int[] firstType = {3,14,-9,10};
		//only be used at the declaration
		
		//second type:
		testArray = new String[50];
		//SPECIAL NOTE: automatically populated with 0s
		//when instantiated unlike Object arrays, which have "null"
		
		//standard 'for' loop
		for(int i = 0; i < testArray.length; i++)
		{
			System.out.println("The "+i+"th item is:"+testArray[i]);
		}
		
		//for each loop (useful only when you don't need to keep track of index
		for(String value:testArray)
		{
			System.out.println(value);
		}		
		/*	 / \
			| | |
			 \ \/
			/ \ \	
			| | |
			 \ /   */
	}
	
	public static void main(String[] args) {
		ArraysMain arrayPractice = new ArraysMain();
	}

	public int diceRoll(int numberOfDice) {
		int sum = 0;
		for(int i = 0; i < numberOfDice; i++)
		{
			sum += (int)(Math.random() * 6) + 1;
		}
		return sum;
	}
}
