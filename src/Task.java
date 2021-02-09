// This is the super class for all the subclasses, implements the Interface method which is in the subclasses
// and also implements the Comparable to later sort the tasklist
public abstract class Task implements TaskInterface, Comparable<Task> {
	String task;
	String date;
	// String date;

	public Task(String task, String date) {
		this.task = task;
		this.date = date;

	}

	//Overrides the Collections.sort function and sorts the ArraList<Task> tasklist 
	public int compareTo(Task tasks) {
		return date.compareTo(tasks.date);
	}

}
