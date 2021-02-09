import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Window {
	private JButton office, home, errands;
	public static ArrayList<JTextField> textField;
	public ArrayList<JButton> clearButton;
	TaskWindow taskWindow = new TaskWindow();
	static JLabel latest;
	static JLabel amount;

	public Window() {

	}

	// Creates the main window with all the panels and buttons
	public void window() {
		JFrame frame = new JFrame("ToDo List");

		JPanel panelTop = new JPanel();
		TopPanel(panelTop);

		JPanel panelLeft = new JPanel();
		LeftPanel(panelLeft);

		JPanel panelRight = new JPanel();
		RightPanel(panelRight);

		JPanel panelDown = new JPanel();
		Downpanel(panelDown);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		frame.add(panelTop, BorderLayout.NORTH);
		frame.add(panelLeft, BorderLayout.WEST);
		frame.add(panelRight, BorderLayout.EAST);
		frame.add(panelDown, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	// Top panel for the buttons
	public void TopPanel(JPanel top) {
		// creates an instance of the actionlistener button_action
		button_action dothings = new button_action();
		JLabel addTask = new JLabel("Add new task: ");
		top.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		top.add(addTask);

		office = new JButton("Office");
		home = new JButton("Home");
		errands = new JButton("Errands");

		// Actionlistener added for the three main buttons
		home.addActionListener(dothings);
		office.addActionListener(dothings);
		errands.addActionListener(dothings);
		top.add(office);
		top.add(home);
		top.add(errands);

	}

	// Left panel for the textfield list and number of task count is displayed
	public void LeftPanel(JPanel left) {
		// creates new Arraylist for the JTextField
		textField = new ArrayList<>();
		amount = new JLabel("Number of task: 0");
		left.setLayout(new GridLayout(0, 1, 5, 5));
		left.setBorder(new EmptyBorder(10, 10, 10, 10));
		left.setPreferredSize(null);
		left.add(amount);
		// runs the loop 10 times to make 10 JTextFields
		for (int i = 0; i < 10; i++) {
			JTextField fields = new JTextField();
			fields.setPreferredSize(new Dimension(450, 30));
			fields.setBackground(Color.WHITE);
			fields.setEditable(false);

			textField.add(fields);
			left.add(fields);
		}

	}
	
	// Right panel for the "clear" buttons
	public void RightPanel(JPanel right) {
		// creates a new ArrayList for the clear buttons
		clearButton = new ArrayList<>();
		// New instance of the delete actionlistener 
		clear_action delete = new clear_action();
		JLabel invisible = new JLabel("");
		right.setLayout(new GridLayout(0, 1, 5, 5));
		right.setBorder(new EmptyBorder(10, 10, 10, 10));
		right.add(invisible);
		// runs the loop to create 10 buttons
		for (int i = 0; i < 10; i++) {
			JButton clear = new JButton("Clear");
			clear.setPreferredSize(new Dimension(80, 0));
			// adds actionlistener to the "clear" button
			clear.addActionListener(delete);
			clearButton.add(clear);
			right.add(clear);

		}
	}
	
	// JLabel at the bottom that changes depending on some changes
	public void Downpanel(JPanel down) {
		latest = new JLabel("Nothing added!");
		down.add(latest);
	}

	// opens a new task window depening on the button pressed
	private class button_action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == office) {
				taskWindow.taskWindow("Office");
			} else if (e.getSource() == home) {
				taskWindow.taskWindow("Home");
			} else if (e.getSource() == errands) {
				taskWindow.taskWindow("Errands");
			}

		}
	}
	
	// this function is to remove a task and put the others in the right order
	private class clear_action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// goes through the button size to give every button an index and able to identify it 
			for (int i = 0; i < clearButton.size(); i++) {
				// if the button pressed matches the index it will run the following code
				if (e.getSource() == clearButton.get(i)) {
					// try/catch so that you wont be able to remove task when the it is empty and so that
					// you wont be able to set the bottom text to the last removed task when empty as well
					try {
						latest.setText("Latest task removed: " + taskWindow.taskList.get(i).Message());
						taskWindow.taskList.remove(i);
					} catch (IndexOutOfBoundsException e1) {
						// TODO Auto-generated catch block
						// Will put out an error message if the "try" doesnt work
						JOptionPane.showMessageDialog(null, "There is nothing to clear here", null,
								JOptionPane.ERROR_MESSAGE);
					}
					
					// Sets a specific textfield empty depending on the tasklist size 
					textField.get(taskWindow.taskList.size()).setText("");
					// Shows the amount of tasks in the tasklist on the window
					amount.setText("Number of task: " + taskWindow.taskList.size());
					// updates the textfieldlist
					taskWindow.updateTaskListl();

				}

			}

		}

	}
}
