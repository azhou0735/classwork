package arrays;

public class Student extends Person {

	public static final String[] grades = {"Freshman","Sophmore","Junior","Senior","Shock Trooper"};
	
	private String grade;
	
	public Student(String first, String last, Borough home, int gradeLevel) {
		super(first, last, home);//super constructor is first because a person must exist before a student can exist
		int index = gradeLevel-9;
		this.grade = grades[index];
	}

	public String toString() {
		//NOTE: if you want to call the parent method, use super.
		return super.toString()+" I am also a "+grade+".";
	}
}
