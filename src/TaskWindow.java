import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaskWindow {
	private JButton Add, Cancel;
	private JTextField text, date;
	public String checker;
	private JFrame popUp;
	public ArrayList<Task> taskList = new ArrayList<>(10);

	// Creates a new task window depending on the button pressed, sets the String checker to either Home, Office, Errands
	public void taskWindow(String checker) {
		this.checker = checker;
		popUp = new JFrame(checker);
		popUp.setSize(300, 200);
		JPanel top = new JPanel();
		TopPanel(top);
		JPanel center = new JPanel();
		CenterPanel(center);
		JPanel bottom = new JPanel();
		BottomPanel(bottom);

		popUp.add(top, BorderLayout.NORTH);
		popUp.add(center, BorderLayout.CENTER);
		popUp.add(bottom, BorderLayout.SOUTH);

		popUp.setVisible(true);

	}
	
	// Recieves a JPanel in the parameters and then creates the TopPanel for the taskwindow
	public void TopPanel(JPanel top) {
		JLabel addTask = new JLabel("Task text");
		text = new JTextField();
		text.setPreferredSize(new Dimension(100, 30));
		top.add(addTask);
		top.add(text);

	}

	// Recieves a JPanel in the parameters and then creates the CenterPanel for the taskwindow
	public void CenterPanel(JPanel center) {
		JLabel addTask = new JLabel("Task date");
		date = new JTextField();
		date.setPreferredSize(new Dimension(100, 30));
		JLabel format = new JLabel("(Format YYYY-MM-DD)");
		center.add(addTask);
		center.add(date);
		center.add(format);

	}

	// Recieves a JPanel in the parameters and then creates the BottomPanelfor the taskwindow
	public void BottomPanel(JPanel bottom) {
		Add = new JButton("Add");
		Cancel = new JButton("Cancel");
		Add.setPreferredSize(new Dimension(75, 20));
		Cancel.setPreferredSize(new Dimension(75, 20));
		//Create a new instance of add_task actionlistener and then add the actionlistener to the "add" button
		add_task newAdded = new add_task();
		Add.addActionListener(newAdded);
		bottom.add(Add);
		//Create a new instance of cancel_task actionlistener and then add the actionlistener to the "cancel" button
		cancel_task canTask = new cancel_task();
		Cancel.addActionListener(canTask);
		bottom.add(Cancel);

	}

	// If tasklist has less than 10 tasks it will add a new task
	public void addT(String checker, String task, String date) {
		if (taskList.size() < 10) {
			// Adds a new object to the tasklist depending on the String which is received
			switch (checker) {
			case "Home":
				taskList.add(new Home(task, date));
				break;
			case "Office":
				taskList.add(new Office(task, date));
				break;
			case "Errands":
				taskList.add(new Errands(task, date));
				break;
			}
			
			//Sets the JLabel latest on the main window to the latest task added
			Window.latest.setText("Latest task added: " + taskList.get(taskList.size() - 1).Message());
			// updates the list after every new task added
			updateTaskListl();
		} else
			// if a user tries to add more than 10 task, a prompt will show up that the tasklist is full
			JOptionPane.showMessageDialog(null, "Tasklist is full", null, JOptionPane.ERROR_MESSAGE);
	}

	// this function is used to update and sort the tasks in the correct order
	public void updateTaskListl() {
		// Sorts the tasklist in the correct order based on the date
		Collections.sort(taskList);
		// for every task in the tasklist set the text to the corresponding textfield
		for (int i = 0; i < taskList.size(); i++) {
			Window.textField.get(i).setText(taskList.get(i).Message());
		}
		// Set the JLabel amount on the main window to the total amount of tasks in the tasklist
		Window.amount.setText("Number of task: " + taskList.size());
	}

	// Actionslistener for the "add" button
	private class add_task implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// if you pressed the add button it will run the following code
			if (e.getSource() == Add) {
				// checks so that all the textfields are not empty before adding a new task
				if (!text.getText().isEmpty() && !date.getText().isEmpty()) {
					// calls the addT funcion to add a new task to the tasklist and then closes the window
					addT(checker, text.getText(), date.getText());
					popUp.dispose();

				} else {
					JOptionPane.showMessageDialog(null,
							"Fill in the textfields!", null,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	// Actionslistener for the "cancel" button
	private class cancel_task implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// if the button you pressed is the cancel button close the taskwindow
			if (e.getSource() == Cancel) {
				popUp.dispose();
			}
		}
	}

}
