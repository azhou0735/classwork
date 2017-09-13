package intro;//this is a package

//this is a class
public class IntroMain {
		
	//constants (public)
	public static final String[] DESCRIPTIONS = {" is a teacher",
			" is a student",
			" is a human being",
			" is imaginary"};
	
	public static void main(String[] args) {
		//declaration of a local variable
		//'new' must be used to call a constructor
		//
		CodingConventions conventionsInstance = new CodingConventions("Andrew Zhou",0);
		
		//instance method call - dependent 
		conventionsInstance.doStuff();
	}

}
