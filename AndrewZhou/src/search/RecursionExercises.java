package search;

public class RecursionExercises {

	public static void main(String[] args) {
		//int value = 10008;
		//System.out.println(value+"! is equal to " +factorial(value));
		//System.out.println(value+" has "+countPrimeFactors(value,2)+" prime factors.");
		towerSolution(4,"A","B","C");
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
	
	private static void towerSolution(int n, String s, String h, String e) {
		if(n <= 1) {
			//System.out.println(s +" "+ h+" " + e);
			System.out.println(s +" to "+ e + " " + n); //base case (center, always exists, A to C)
		}else { //below has to print out (2^n)-2 (above uses 1)
			//System.out.println(s +" "+ h+" " + e);
			//B is always in the same position when in reverse
			towerSolution(n-1,s,e,h); // <-- priority is how to replicate the 2^n -1 count
			System.out.println(s +" to "+ e + " " + n); //this is A to C which is the center of all the solutions
			towerSolution(n-1,h,s,e); // <-- this is the 2^n
		}
	}
	
}
