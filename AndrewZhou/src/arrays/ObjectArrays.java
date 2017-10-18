package arrays;

public class ObjectArrays {
	
	public ObjectArrays() {
		Person[] people = new Person[20];
		populate(people);
		for(Person p:people)
		{
			System.out.println(p);
		}
	}

	private void populate(Person[] people) {
		for(int i = 0; i < people.length; i++)
		{
			String firstName = randomNameFrom(Person.FIRST_START, Person.FIRST_MIDDLE, Person.FIRST_END);
			String lastName = randomNameFrom(Person.LAST_START, Person.LAST_MIDDLE, Person.LAST_END);
			Borough b = randomBorough();
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
	
	
}
