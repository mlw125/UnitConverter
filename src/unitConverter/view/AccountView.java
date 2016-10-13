package unitConverter.view;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;

import unitConverter.controller.AccountController;
import unitConverter.model.AccountModel;
import unitConverter.model.ModelEvent;

public class AccountView extends JFrameView {
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static final String EDITUS = "Edit in US"; 
	public static final String EDITEURO = "Edit in Euros";
	public static final String EDITYUAN = "Edit in Yuan";
	public static final String DEPOSIT = "Create Deposit Agent";
	public static final String WITHDRAW = "Create Withdraw Agent";
	
	// the combo box
	public static JComboBox<String> comboBox = new JComboBox<String>();
	
	// the dialog box
	public JDialog error;
	// for checking for severe errors
	private boolean severeError = false;
	
	//private String operation = PLUS; 
	public AccountView(AccountModel model, AccountController controller) { 
		super(model, controller);

		this.setTitle("Simple Account");		
		this.getContentPane().add(comboBox, BorderLayout.EAST);
		
		JPanel buttonPanel = new JPanel();
		//JPanel agentPanel = new JPanel(); 
		Handler handler = new Handler();
		JButton jButtonSave = new JButton(SAVE); 
		jButtonSave.addActionListener(handler);
		JButton jButtonExit = new JButton(EXIT); 
		jButtonExit.addActionListener(handler); 
		JButton jButtonUS = new JButton(EDITUS); 
		jButtonUS.addActionListener(handler); 
		JButton jButtonEuro = new JButton(EDITEURO); 
		jButtonEuro.addActionListener(handler); 
		JButton jButtonYuan = new JButton(EDITYUAN); 
		jButtonYuan.addActionListener(handler);
		JButton jButtonDeposit = new JButton(DEPOSIT); 
		jButtonDeposit.addActionListener(handler);
		JButton jButtonWithraw = new JButton(WITHDRAW); 
		jButtonWithraw.addActionListener(handler);
		
		buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
		buttonPanel.add(jButtonSave);
		buttonPanel.add(jButtonExit);
		buttonPanel.add(jButtonUS);
		buttonPanel.add(jButtonEuro);
		buttonPanel.add(jButtonYuan);
		buttonPanel.add(jButtonDeposit);
		buttonPanel.add(jButtonWithraw);
		
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		pack();
	 } // end constructor
	
	public void modelChanged(ModelEvent event) {
		// if the message contains E, then that means error and this view will
		// handle the event
		if(event.getMessage().equals("E")) {
			if(event.getTitle().equals("NUM")) { // number formatting error
				errorBox("NUM");
			} else if(event.getTitle().equals("NEG")) { // negative number for amount
				errorBox("NEG");
			} else if(event.getTitle().equals("LOAD")) { // loading file error, is severe
				errorBox("LOAD");
			} else if(event.getTitle().equals("SAVE")) { // saving file error, is severe
				errorBox("SAVE");
			} else if(event.getTitle().equals("ID")) { // for not having a unique thread id
				errorBox("ID");
			} else if(event.getTitle().equals("EM")) { // for having empty fields in a StartAgent window
				errorBox("EM");
			}// end if/else
		} else if (event.getMessage().equals("IC")) { // if the message is for initializing the view
			initComboBox();
		} else if (event.getMessage().equals("RT")) { // if the message says to create a RunAgent window
			// find the corresponding start agent window
			int index = findStartWindow(event.getTitle());
		}
	} // end modelChanged()
	
	// for finding s specific StartAgent window, based on id
	private int findStartWindow(String id) {
		/*
		for(int x = 0; x < startList.size(); x++) {
			if(startList.get(x).getId().compareTo(id) == 0) {
				return x;
			}
		}
		*/
		return -1;
	} // end findStartWindow()

	// Inner classes for Event Handling 
	class Handler implements ActionListener {
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			// catch mainly used when there is an error reading the file, this way the program can get to the part
			// where the dialog box can be displayed
			try {
				((AccountController)getController()).operation(e.getActionCommand(), comboBox.getSelectedItem().toString()); 
			} // end try
			catch (NullPointerException error) {
				((AccountController)getController()).operation(e.getActionCommand(), ""); 
			} // end catch
	    } // end actionPerformed
	} // end class Handler
	
	// main function
	public static void main(String [] args) {
		//SwingUtilities.invokeLater(new Runnable() {			
			//public void run() {
				try {
					// read text file
					new AccountController("test.txt");
				} // end try
				catch (IllegalArgumentException e) { // if the file is missing
					new AccountController("not found");
				} // end catch
			//}
		//});
	} // end main
	
	// Initializes the combo box and the windows that are needed for the accounts
	public void initComboBox() {
		/*
		// get all the window titles in a string array
		String [] titles = ((AccountController)getController()).getTitles();
		
		// loop through the array
		for(int x = 0; x < titles.length; x++) {
			// add the titles to the combo box
			comboBox.addItem(titles[x]);
			
			// go to Controller so that the data can be sent from the model
			((AccountController)getController()).operation("Initialize", titles[x]);
		} // end for
		
		// set the default selection at the first one
		comboBox.setSelectedIndex(0);
		*/
	} // end initComboBox()
	
	// for creating the error dialog box
	public void errorBox(String errorType) {
		// string hold the message
		String message = "Test";
		// if the error is number formatting
		if(errorType == "NUM") {
	    	message = "Only Numbers are allowed";
	    } // end if
		// if the error is a negative number
		else if(errorType == "NEG") {
	    	message = "Amount Cannot Not Be Negative";
	    } // end else if
		// if the number is from file loading
		else if(errorType == "LOAD") {
	    	message = "Error on Loading File";
	    	severeError = true;
	    } // end else if
		// if the error is from file saving
		else if(errorType == "SAVE") {
	    	message = "Error on saving File";
	    	severeError = true;
	    } // end else if
		// if the error is not having a unique thread id
		else if(errorType == "ID") {
	    	message = "Agent ID must be Unique";
	    } // end else if
		// if the error is having empty fields for a StartAgent window
		else if(errorType == "EM") {
	    	message = "A field cannot be left empty";
	    } // end else if
		
		// create new dialog box()
		error = new JDialog();
		
		// set up the box
		JTextField messagePanel = new JTextField();
		messagePanel.setText(message);
		messagePanel.setEditable(false);
		messagePanel.setOpaque(false);
		
	    Handler handler = new Handler();
	    JPanel buttonPane = new JPanel();
	    JButton button = new JButton("OK");
	    buttonPane.add(button);
	    button.addActionListener(handler);
	    error.setLayout(new GridLayout(4, 4));
	    error.add(messagePanel, null);
	    error.add(buttonPane, null);
	    error.pack();
	    
	    error.setVisible(true);
	    error.setAlwaysOnTop(true);
	} // end errorBox()
	
	// controller will call this function, based on severeError status 
	// the view will close the box or end the program
	public void hideError() {
		// end the program
		if (severeError == true) {
			System.exit(0);
		} // end if
		// hide the dialog box
		else {
			error.setVisible(false);
		} // end else
	} // end hideError()
} // end class AccountView
