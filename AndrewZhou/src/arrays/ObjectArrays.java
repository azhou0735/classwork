package arrays;

public class ObjectArrays {
	
	public ObjectArrays() {
		Person[] people = new Person[30];
		populate(people);
		//people[0] = new Thing("coffee maker");
		Person[] z = selectGroup(people,20);
		Person[] y = selectGroup(people,20);
		for(Person p:people)
		{
			p.mingle(people);
			p.printFriends();
			System.out.println("");
		}
			
		System.out.println(countDifferences(y,z));
	}

	public double countDifferences(Person[] arr1, Person[] arr2) {
		int differencesCount = 0;
		for(int i = 0; i < arr1.length; i++)
		{
			if(arr1[i]!=arr2[i])
				differencesCount++;
		}
		return differencesCount;
	}
	
	public void testShuffling(Person[] people)
	{
		int sum = 0;
		for(int i = 0; i < 100; i++)
		{
			sum += countDifferences(selectGroup(people,20),selectGroup(people,20));
		}
		System.out.println(sum/100);
	}
	
	private void populate(Object[] people) {
		for(int i = 0; i < people.length; i++)
		{
			String firstName = randomNameFrom(Person.FIRST_START, Person.FIRST_MIDDLE, Person.FIRST_END);
			String lastName = randomNameFrom(Person.LAST_START, Person.LAST_MIDDLE, Person.LAST_END);
			Borough b = randomBorough();
			
			//Have multiple data types in Object[] (sub-classes of the declared type)
			if(Math.random() < 0.6) {
				//60% of the time...
				int grade = (int)(Math.random()*5)+9;
				people[i] = new Student(firstName,lastName,b,grade);
			}else {
				//the other 40% of the time...
				people[i] = new Person(firstName, lastName, b);
			}
		}
	}

	private Borough randomBorough() {
		return Borough.NY_BOROUGHS[(int)(Math.random()*Borough.NY_BOROUGHS.length)];
	}

	private String randomNameFrom(String[] start, String[] middle, String[] end) {
		return get(start)+get(middle)+get(end);
	}

	private String get(String[] arr) {
		return arr[(int)(Math.random()*arr.length)];
	}
	
	public Person[] selectGroup(Person[] population, int length){
		Person[] returnArr = new Person[length];
		if(population.length == length)
			return population;
		for(int i = 0; i < returnArr.length; i++) {
			returnArr[i] = population[(int)(Math.random()*population.length-1)];
			for(int j = 0; j < returnArr.length; j++) {
				if(returnArr[j] == returnArr[i] && j != i)
				{
					i--;
					break;
				}
			}
		}
		return returnArr;
	}
	
	public static boolean personInGroup(Person[] a, Person b) {
		for(int j = 0; j < a.length; j++) {
			if(a[j] == b)
			{
				return true;
			}
		}
		return false;
	}
}
