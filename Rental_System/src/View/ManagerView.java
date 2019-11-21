package View;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ManagerView extends GUIView{

	private static final long serialVersionUID = 1L;
	private JLabel mLabel = new JLabel("Manager Interface");
	private JButton changePeriod = new JButton("Change Period of Fees");
	private JButton changeState = new JButton("Change State of a Property");
	private JButton changeFees = new JButton("Set Fees");
	private JButton summary = new JButton("Get Summary");
	private JButton list = new JButton("List All Properties");
	
	private JTextArea text = new JTextArea();
	private JScrollPane textScroll = new JScrollPane(text);
	private JPanel mainPanel;
	
	private JPanel loginPanel;
	private JLabel loginTitle = new JLabel("Please Login as one of the Following");
	private JButton newUser = new JButton("New Manager");
	private JButton existingUser = new JButton("Existing Manager");

	private JPanel newPanel;
	private JLabel newTitle = new JLabel("Please enter your desired username and password.");
	private JTextField newUsername = new JTextField(10);
	private JTextField newPassword = new JTextField(10);
	
	
	private JPanel existPanel;
	private JLabel existTitle = new JLabel("Please enter your username and passord.");
	private JTextField existUsername = new JTextField(10);
	private JTextField existPassword = new JTextField(10);
	
	
	
	public ManagerView(){
		displayLogin();
	}
	
	@Override
	public void display() {
		mainPanel = new JPanel();
		
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.add(mLabel);
		text.setEditable(false);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setPreferredSize(new Dimension(450, 365));
		
		mainPanel.add(textScroll);
		mainPanel.add(changePeriod);
		mainPanel.add(changeState);
		mainPanel.add(changeFees);
		mainPanel.add(summary);
		mainPanel.add(list);

		this.add(mainPanel);
	}
	
	
	public void displayLogin() {
		loginPanel = new JPanel();
		this.setSize(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginPanel.add(loginTitle);
		loginPanel.add(newUser);
		loginPanel.add(existingUser);

		this.add(loginPanel);
	}
	
	public void addListListener(ActionListener l) {
		list.addActionListener(l);
	}
	
	public void addNewListener(ActionListener newManager) {
	newUser.addActionListener(newManager);
	}
	
	public void addExistListener(ActionListener exist) {
	existingUser.addActionListener(exist);
	}
	
	public void addChangePeriodListener(ActionListener cp) {
		changePeriod.addActionListener(cp);
	}
	public void addChangeFeeListener(ActionListener cf) {
		changeFees.addActionListener(cf);
	}
	public void addSummaryListener(ActionListener summ) {
		summary.addActionListener(summ);
	}
	public void addChangeStateListener(ActionListener st) {
		changeState.addActionListener(st);
	}

	public void closeLogin() {
		loginPanel.setVisible(false);
		
	}

	public int displayNewUser() {
		newPanel = new JPanel();

		newPanel.add(newTitle);
		newPanel.add(newUsername);
		newPanel.add(newPassword);

		int result = JOptionPane.showConfirmDialog(null, newPanel, "", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
		}
	
	
	public boolean newIsEmpty() {
		if (getNewUsername().isEmpty() || getNewPassword().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public String getNewUsername() {
		return newUsername.getText();
	}

	public String getNewPassword() {
		return newPassword.getText();
	}
	
	
	
	public int displayExistingUser() {
		existPanel = new JPanel();

		existPanel.add(existTitle);
		existPanel.add(existUsername);
		existPanel.add(existPassword);

		int result = JOptionPane.showConfirmDialog(null, existPanel, "", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public boolean existIsEmpty() {

		if (getExistUsername().isEmpty() || getExistPassword().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public String getExistUsername() {
		return existUsername.getText();
	}
	
	public String getExistPassword() {
		return existPassword.getText();
	}

	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void writeText(String s) {
		text.append(s);
	}
	public void clearDisplay() {
		text.setText(null);
	}
	
}
