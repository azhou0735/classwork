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
		//populate1ToN(intRay);
		//swap(intRay, 0, 1);
		//cycleThrough(intRay,10);
		//System.out.println(countLessThan(intRay,20));
		//System.out.println(Arrays.toString(intRay));
		
		int[] randomRolls = new int[1000];
		populate(randomRolls);
		int[] result = longestConsecSeqAndPos(randomRolls);
		System.out.println("The longest sequence of dice rolls is " + result[0] +
				" it happend on the " + (result[1]+1) + "th roll. Starting with a roll of "+randomRolls[result[1]]+".");
		System.out.println(Arrays.toString(result));
		
	}
	
	private int[] longestConsecSeqAndPos(int[] intRay) 
	{
		int longestSequence = 1;
		int currentSequence = 1;
		int longestSequenceStart = 0;
		int longestSequenceEnd = 0;
		int currentSequenceStart = 0;
		for(int i = 1; i < intRay.length; i++)
		{
			if(intRay[i-1] == intRay[i]-1)
			{
				currentSequence++;
			}
			else
			{
				if(currentSequence > longestSequence)
				{
					longestSequence = currentSequence;
					longestSequenceEnd = i;
					longestSequenceStart = currentSequenceStart;
				}
				currentSequenceStart = i;
				currentSequence = 1;
			}
			if((intRay[i-1] == intRay[i]-1) && i == intRay.length-1)
			{
				if(currentSequence > longestSequence)
					{
					longestSequence = currentSequence;
					longestSequence = currentSequence;
					}
			}
		}
		int[] returnArray = new int[longestSequenceEnd - longestSequence + 1];
		for(int i = 1; i<returnArray.length; i++)
		{
			returnArray[i] = intRay[longestSequenceEnd - longestSequence+i-1];
		}
		returnArray[0] = longestSequenceStart;
		return returnArray;
	}

	public int longestConsecutiveSequence(int[] intRay) {
		int longestSequence = 1;
		int currentSequence = 1;
		for(int i = 1; i < intRay.length; i++)
		{
			if(intRay[i-1] == intRay[i]-1)
			{
				currentSequence++;
			}
			else
			{
				if(currentSequence > longestSequence)
					longestSequence = currentSequence;
				currentSequence = 1;
			}
			if((intRay[i-1] == intRay[i]-1) && i == intRay.length-1)
			{
				if(currentSequence > longestSequence)
					longestSequence = currentSequence;
			}
		}
		return longestSequence;
	}
	
	
	public void cycleThrough(int[] intRay, int n) {
		while(n!=0)
		{
			frontToBack(intRay);
			n--;
		}
	}
	
	public void frontToBack(int[] intRay){
		int frontInt = intRay[0];
		for(int i = 1; i < intRay.length; i++) {
			swap(intRay, i, i-1);
		}
		intRay[intRay.length-1] = frontInt;
		/*for(int i = 0; i < intRay.length; i++){
		 swap(intRay, i, i+1);}
		 */
	}

	public void reverseOrderOriginal(int[] intRay) {
		for(int i = 0; i < intRay.length/2; i ++)
		{
			swap(intRay,i,intRay.length-i-1);
		}
	}
	
	public int[] reverseOrder(int[] intRay) {
		int[] returnArray = new int[intRay.length];
		for(int i = 0; i < intRay.length; i++)
		{
			returnArray[i] = intRay[intRay.length-1-i]; 
		}
		return returnArray;
	}
	
	public int countLessThan(int[] intRay, int n)
	{
		int count = 0;
		for(int i = 0; i < intRay.length; i++)
		{
			if(intRay[i] < n)
			{
				count ++;
			}
		}
		/*for(int value:intRay){
			if(value<n)count++;
		}*/
		return count;
	}
	
	public void shuffle(int[] intRay) {
		for(int i = 0; i < intRay.length; i ++)
		{
			//swap(intRay, i , (int)(Math.random() * intRay.length) + 0);
			swap(intRay, (int)(Math.random() * intRay.length) + 0 , (int)(Math.random() * intRay.length) + 0);
		}
	}

	public void swap(int[] intRay, int i, int j) {
		int temp = intRay[i];
		intRay[i] = intRay[j];
		intRay[j] = temp;
	}

	public void populate1ToN(int[] intRay) {
		for(int i = 0; i < intRay.length; i++)
		{
			intRay[i] = i+1;
		}
	}

	public void checkOccurences(int[] arr, int start, int end) {
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
