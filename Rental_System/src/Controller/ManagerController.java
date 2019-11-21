package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Application;
import View.GUIView;
import View.ManagerView;

public class ManagerController {

	
	ManagerView manager;
	Application app;
	
	
ManagerController(GUIView view, Application model){
	manager =  (ManagerView) view;
	app = model;
	
	manager.addChangeStateListener(new ChangeStateListener());
	manager.addSummaryListener(new SummaryListener());
	manager.addChangeFeeListener(new ChangeFeeListener());
	manager.addChangePeriodListener(new ChangePeriodListener());
	manager.addNewListener(new NewListener());
	manager.addExistListener(new ExistListener());
	manager.addListListener(new ListListener());
}
	class ChangeStateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change state");
			
		}
	}
	
	class ListListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("List all");
		}
		
	}
	
	class SummaryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Summary");
			
		}
	}
	
	class ChangeFeeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change fees");
			
		}
	}
	
	class ChangePeriodListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Change period");
		}
	}
	
	class NewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("New manager");
			int result = manager.displayNewUser();

			if (result == 1 && manager.newIsEmpty() == false) {
				manager.closeLogin();
				String userName = manager.getNewUsername();
				String password = manager.getNewPassword();
			app.addNewManager(userName, password);
			
				manager.display();
			} else if (result == 1 && manager.newIsEmpty() == true) {
				manager.showMessage("One or more fields missing");
			}
			
		}
		
	}
	
	class ExistListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Exist manager");
			int result = manager.displayExistingUser();

			if (result == 1 && manager.existIsEmpty() == false) {
				String username = manager.getExistUsername();
				String password = manager.getExistPassword();
			
				
				boolean check = app.checkManagerExist(username, password);
				if (check == true) {
					manager.closeLogin();
					manager.display();
				} else {
					manager.showMessage("Incorrect username or password.");
				}
			}  if (result == 1 && manager.existIsEmpty() == true) 
				manager.showMessage("Missing one or more fields");
			
		
		}
		
	}

}
