package arrays;

public class Person {
	
	private String firstName;
	private String lastName;
	private Borough home;
			
	public Person(String f, String l, Borough h) {
		this.firstName = f;
		this.lastName = l;
		this.home = h;
	}
	
	public String toString() {
		return "My name is "+firstName+" "+lastName+" and I live in "+home+".";
	}
}
