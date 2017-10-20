package arrays;

import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
	//	ObjectArrays oA = new ObjectArrays();
		Person p = new Person("Hello", "World", new Borough("The Solar System"));
		int number = 3;
		int[] intRay = {7,7,7,7};
		test(p, number, intRay);
		System.out.println(p+", "+number+", "+Arrays.toString(intRay));
	}
	/**
	 * PASS-BY-VALUE EXAMPLES
	 * DOES CHANGE THE NAME
	 * ALSO CHANGES THE ARRAY
	 * NO VOID METHOD CAN PERMANENTLY CHANGE AN INT
	 * (you can change it if you set it as a return type)
	 * */
	
	public static void test(Person s, int x, int[] arr) {
		//String name = s.getFirstName();
		String name = "Goodbye";
		s.setFirstName(name); // < - Uses the field to change the original value
		x = 5;
		//arr = new int[3]; // < - If you want to change original, don't create a new array
		arr[0] = 1; // < - References the original array
		arr[1]= 2;
		arr[2] = 3;
	}
	public static void test2(Person s, int x, int[] arr) {
		String name = s.getFirstName();
		name = "Goodbye";
		x = 5;
		arr = new int[3];
		arr[0] = 1;
		arr[1]= 2;
		arr[2] = 3;
	}
}
