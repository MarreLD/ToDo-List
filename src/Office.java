// Office inherits from the Task super class
public class Office extends Task {
	
	private String office = " Office: ";

	public Office(String task, String date) {
		super(task, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	// Method from the interface which fetches the date, task name and task text 
	public String Message() {
		// TODO Auto-generated method stub
		return date.toString() + office + task;
	}

}
