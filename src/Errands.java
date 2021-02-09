// Errands inherits from the Task super class
public class Errands extends Task {
	
	private String errands = " Errands: ";

	public Errands(String task, String date) {
		super(task, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	// Method from the interface which fetches the date, task name and task text 
	public String Message() {
		// TODO Auto-generated method stub
		return date.toString() + errands + task;
	}

}
