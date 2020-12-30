package assignmentAVL;

//importing the packages
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import java.awt.FlowLayout;

/* creating class
 extending class
 */
public class UI extends JFrame{

	private static final long serialVersionUID = 1L;
	//	private JButton btnAddNumber;
	//	private JTextField textField;


	private final JButton addRandButton;
	private final TreeLogic treeLogic;

	private final TreePanel treePanel;
	private final JTextField valueField;
	private final JButton addButton;

	private final JButton clearButton;

	private final JButton removeButton;

	private final JButton searchButton;
	private final JPanel buttonPanel;

	private final JLabel logField;

	//creating GUI
	public UI(){
		JFrame frame = new JFrame("AVL Tree-Balanced Binary Tree");
		frame.setSize(Parameters.WIDTH + 50, Parameters.HEIGHT);
		frame.setResizable(false);
		treeLogic = new TreeLogic();

		treePanel = new TreePanel();

		JPanel logPanel = new JPanel();

		logField = new JLabel("add new value, please");
		logField.setFont(Parameters.BUTTON_FONT);
		logPanel.add(logField);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		addButton = new JButton("Add Value");
		addButton.setBackground(Parameters.COLOR_BUTT_CREATE);
		addButton.setFont(Parameters.BUTTON_FONT);
		
		removeButton = new JButton("Remove");
		removeButton.setBackground(Parameters.COLOR_BUTT_REMOVE);
		removeButton.setFont(Parameters.BUTTON_FONT);
		
		searchButton = new JButton("Search");
		searchButton.setBackground(Parameters.COLOR_BUTT_CREATE);
		searchButton.setFont(Parameters.BUTTON_FONT);
		
		addRandButton = new JButton("Add Rand Value");
		addRandButton.setBackground(Parameters.COLOR_BUTT_CREATE);
		addRandButton.setFont(Parameters.BUTTON_FONT);
		
		
		clearButton = new JButton("Clear");
		clearButton.setBackground(Parameters.COLOR_BUTT_CLEAR);
		clearButton.setFont(Parameters.BUTTON_FONT);

		valueField = new JTextField("");
		valueField.setColumns(7);
		valueField.setFont(Parameters.TEXT_FIELD_FONT);
		valueField.setHorizontalAlignment(JTextField.CENTER);
		
		buttonPanel.add(valueField);
		buttonPanel.add(addButton);
		buttonPanel.add(addRandButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(clearButton);
		buttonPanel.setBackground(Parameters.COLOR_PANEL);
		
		treePanel.setBackground(Color.WHITE);
		
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		frame.getContentPane().add(treePanel, BorderLayout.CENTER);
		frame.getContentPane().add(logPanel, BorderLayout.SOUTH);

		addEvents();
		frame.setVisible(true);
	
	}

	public void paint(Graphics g){
		// call superclass version of method paint
		super.paint(g);
	}

	public void addEvents () {
		addRandButton.addActionListener(arg0 -> {
			// EVENT FOR THE ADD BUTTON
			int value = (int) (Math.random()*100+1);

			addProceed(value);
		});

		addButton.addActionListener(arg0 -> {
			// EVENT FOR THE ADD Value BUTTON
//				string

			int value;

			try {
				value = Integer.parseInt(valueField.getText());
			} catch (NumberFormatException e) {
				System.out.println("not a number");
				logField.setText("'" + valueField.getText() + "' is not a number");
				return;
			}

			if (value > 999 || value < -99) {
				System.out.println("to many symbols");
				logField.setText("proposed rage: from -99 to 999");
				return;
			}

			addProceed(value);

		});
		
		searchButton.addActionListener(arg0 -> {
			int value;

			try {
				value = Integer.parseInt(valueField.getText());
			} catch (NumberFormatException e) {
				System.out.println("not a number");
				logField.setText("'" + valueField.getText() + "' is not a number");
				return;
			}

			Tree result = treeLogic.searchNode(value);
			if (result == null) {
				logField.setText("node " + value + " is not exist");
			} else {
				logField.setText("node " + value + " is found");
			}
			treePanel.repaint();
		});
		
		removeButton.addActionListener(arg0 -> {
			int value;

			try {
				value = Integer.parseInt(valueField.getText());
			} catch (NumberFormatException e) {
				System.out.println("not a number");
				return;
			}


			TreeLogic.setAdded(-1000);
			TreeLogic.setRemoved(value);

			Tree forRemove = treeLogic.searchNode(value);
			if (forRemove == null) {
				logField.setText("node " + value + " is not exist");
				return;
			}

			TreeLogic.setRemoved(value);
			treePanel.repaint();

			enableComponents(buttonPanel, false);
			new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						treeLogic.removeNode(forRemove);
						treePanel.repaint();
						enableComponents(buttonPanel, true);
						logField.setText("node " + value + " removed");
						// your code here
					}
				},
				2000
			);



		});
		
		clearButton.addActionListener(arg0 -> {
//				System.out.println("CLEAR");
			treeLogic.clearTree();
			treePanel.repaint();
			logField.setText("cleared");
		});


	}
	
	private void addProceed (int value) {
		treeLogic.addNode(value);
		treePanel.repaint();
		logField.setText("node " + value + " added");
		
		Tree localTree =  treeLogic.searchNode(TreeLogic.getTree(), value);
		Tree degTree = treeLogic.chekDeg(localTree);
		
		if (degTree != null){
			TreeLogic.setDegenerated(degTree.getValue());
			logField.setText("node " + value + " added with ...");
			enableComponents(buttonPanel, false);
			new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						TreeLogic.setDegenerated(-1000);
						String rotateType = treeLogic.typeOfRotation(degTree);
						treePanel.repaint();							
						enableComponents(buttonPanel, true);
						logField.setText("node " + value + " added with " + rotateType);
					}
				}, 
				2000 
			);		
		}
		
		
	}
	
	private void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        
        for (Component component : components) {
            component.setEnabled(enable);
        }
    }

}
