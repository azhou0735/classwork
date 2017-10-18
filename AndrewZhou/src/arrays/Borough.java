package arrays;

public class Borough {
	
	public static final Borough[] NY_BOROUGHS = {new Borough("Brooklyn"), new Borough("Bronx"), new Borough("Manhattan"), new Borough("Queens"), new Borough("Staten Island")};

	private String name;
	
	public Borough(String n) {
		this.name = n;
	}
	
	public String toString() {
		return name;
	}
}
