package arrays;

public class Person {
	
	public static final String[] FIRST_START = {"Chr","M","L","Gr","S","Ph","And","St","Cth"};
	public static final String[] FIRST_MIDDLE = {"isti","icha","ere","eta","are","an","ina","eta","y","ulu"};
	public static final String[] FIRST_END = {"","na","n","r","tian","s","rs","mp","les"};
	
	public static final String[] LAST_START = {"Tr","Br","L","Gr","S","Ph","Sh","St"};
	public static final String[] LAST_MIDDLE = {"om","o","on","ola","et","e","ina","eta","y"};
	public static final String[] LAST_END = {"","son","n","ers","rian","ston","ck","mp","sk"};
	
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
