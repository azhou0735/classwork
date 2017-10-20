package arrays2D;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] arr = {0,1, 2, 3};
		changeNeighbours(arr, 5);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void changeNeighbours(int[] arr, int idx) {
		if(idx < arr.length) {
			if(idx+1<arr.length) {
				arr[idx+1] = arr[idx+1]-1; // arr[idx+1] --;
			}
			if(idx-1>-1) {
				arr[idx-1] = arr[idx-1]-1; // arr[idx-1] --;
			}
				arr[idx] = arr[idx] +1; // arr[idx] ++;
		}
	}
}
