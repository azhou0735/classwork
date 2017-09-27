package attendance;

public class Student implements Attendee {
	private String firstName;
	private String lastName;
	private boolean presentStatus;
	public Student(String first, String last)
	{
		firstName = first;
		lastName = last;
		presentStatus = false;
	}
	public boolean isPresent() {
		return presentStatus;
	}
	public void setPresent(boolean present) {
		if(present)
		{
			presentStatus = true;
		}
		else
		{
			presentStatus = false;
		}
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public boolean mathces(String first, String last) {
		return (this.lastName.toLowerCase().equals(last.toLowerCase()) && this.firstName.toLowerCase().equals(first.toLowerCase()));
	}
	public boolean matches(String last) {
		return this.lastName.toLowerCase().equals(last.toLowerCase());
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
		if(status)
		{
			return returnString + "PRESENT \n";
		}
		else{
			return returnString + "ABSENT \n";
		}
		
	}
	
}
