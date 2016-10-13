package unitConverter.controller;
import unitConverter.model.AccountModel;
import unitConverter.view.AccountView;
import unitConverter.view.JFrameView;

public class AccountController extends AbstractController {
	public AccountController(String filename){
		setModel(new AccountModel());
		setView(new AccountView((AccountModel)getModel(), this));
		((JFrameView)getView()).setSize(500, 200);
		((JFrameView)getView()).setVisible(true);
		
		// load the data from the file, this will update the view as well
		//((AccountModel)getModel()).load(filename);
	}
	
	// for AccountView operations
	public void operation(String option, String title){		
		/*
		if(option.equals(AccountView.SAVE)){ // pressing save
			((AccountModel)getModel()).save();
		} else if(option.equals(AccountView.EXIT)){ // pressing exit
			((AccountModel)getModel()).exit();
		} else if(option.equals(AccountView.EDITUS)){ // opening us window
			((AccountModel)getModel()).openWindow(title, "OU");			
		} else if(option.equals(AccountView.EDITEURO)){ // opening euro window
			((AccountModel)getModel()).openWindow(title, "OE");
		} else if(option.equals(AccountView.EDITYUAN)){ // opening yuan window
			((AccountModel)getModel()).openWindow(title, "OY");
		} else if(option.equals(AccountView.DEPOSIT)){ // for a agent that will start a deposit thread
			((AccountView)getView()).threadStart(title, "D");
		} else if(option.equals(AccountView.WITHDRAW)){ // for a agent that will start a withdraw thread
			((AccountView)getView()).threadStart(title, "W");
		} else if(option.equals("Initialize")){ // Initializing the main view, not a button 
			((AccountModel)getModel()).initData(title);
		} else if(option.equals("OK")){ // for closing dialog box
			((AccountView)getView()).hideError();		
		} // end if/else
		*/
	} // end operation()

	// for buttons on the us windows
	public void operationUS(String actionCommand, String title, String amount) {
		/*
		if(actionCommand.equals("Dismiss")) { // dismiss button
			((AccountModel)getModel()).closeWindow(title, "CU");
		} else if (actionCommand.equals("Deposit")) { // deposit button
			// maybe have these return true or false
			((AccountModel)getModel()).deposit("U", title, amount);
		} else if (actionCommand.equals("Withdraw")) { // withdraw button
			((AccountModel)getModel()).withdraw("U", title, amount);
		} // end if/else
		*/
	} // end operationUS
} // end class AccountController
