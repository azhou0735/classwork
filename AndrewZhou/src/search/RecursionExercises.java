package search;

public class RecursionExercises {

	public static void main(String[] args) {
		int value = 10008;
		System.out.println(value+"! is equal to " +factorial(value));
		System.out.println(value+" has "+countPrimeFactors(value,2)+" prime factors.");
	}

	private static int countPrimeFactors(int value, int i) {
		if(value == 1) {
			return 0;
		}else if(value%i == 0) {
			return 1+countPrimeFactors(value/i,2);
		}else {
			return countPrimeFactors(value,i+1);
		}
	}

	private static long factorial(int value) {
		if(value == 0) {
			return 1;
		}else {
			return value*factorial(value-1);
		}
	}
	
	
	
}
