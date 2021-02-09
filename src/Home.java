// Home inherits from the Task super class
public class Home extends Task {

	private String home = " Home: ";

	public Home(String task, String date) {
		super(task, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	// Method from the interface which fetches the date, task name and task text 
	public String Message() {
		// TODO Auto-generated method stub
		return date.toString() + home + task;
	}

}
