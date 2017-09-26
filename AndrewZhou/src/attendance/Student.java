package attendance;

public class Student implements Attendee {
	private String firstName;
	private String lastName;
	private boolean presentStatus = false;
	public Student(String first, String last)
	{
		String firstName = first;
		String lastName = last;
	}
	public boolean isPresent() {
		return presentStatus;
	}
	public void setPresent(boolean present) {
		if(present)
		{
			presentStatus = true;
		}
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public boolean mathces(String first, String last) {
		return first.toLowerCase().equals(last.toLowerCase());
	}
	public boolean matches(String last) {
		return this.getLastName().toLowerCase().equals(last.toLowerCase());
	}
	public String getReportString() {
		String first = this.getFirstName();
		String last = this.getLastName();
		boolean status = this.isPresent();
		String returnString;
		if(this.getLastName().length()>20)
		{
			last = this.getLastName().substring(0,17) + "...";
		}
		returnString = last;
		for(int i = returnString.length(); i<20; i++)
		{
			returnString += " ";
		}
		if(this.getLastName().length()>20)
		{
			first = this.getFirstName().substring(0,17) + "...";
		}
		returnString += first;
		for(int i = returnString.length(); i<40; i++)
		{
			returnString += " ";
		}
		if(this.isPresent())
		{
			return returnString + "PRESENT \n";
		}
		else{
			return returnString + "ABSENT \n";
		}
		
	}
	
}
