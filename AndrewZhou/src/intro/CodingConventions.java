package intro;

//class is a file describing a new data type
public class CodingConventions {
	
	
	//fields
	private String name;
	private String description;
	
	//this is a special method, a "constructor"
	//no return type - thing it returns is an instance of the class
	/* 
	 * therefore the name of a constructor must always
	 * match the name of the class
	 */
	public CodingConventions() {
		//fields are instantiated in a constructor
		name = "Andrew Zhou";
		description = " is a studnet";
	}
}
