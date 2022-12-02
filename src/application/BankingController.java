package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingController {
	Stage applicationStage;
    private double funds;
    private Label availFunds = new Label("Available funds: $" + funds);
	
    @FXML
    private TextField fundsTextfield;
    
    @FXML
    void mainMenu(ActionEvent event) { // first "Next" button
    	VBox mainMenuContainer = new VBox();
    	VBox addFundsContainer = new VBox();
    	VBox subtractFundsContainer = new VBox();
    	Scene mainMenuScene = new Scene(mainMenuContainer);
    	Scene addFundsScene = new Scene(addFundsContainer);
    	Scene subtractFundsScene = new Scene(subtractFundsContainer);
    	funds = Double.parseDouble(fundsTextfield.getText());
    	
    	// main menu widgets
    	Label mainMenuTitle = new Label("Main Menu");
    	//availFunds.setText("Available funds: $" + funds);
    	Button addFundsButton = new Button("Add to funds");
    	addFundsButton.setOnAction(e -> applicationStage.setScene(addFundsScene));
    	Button subtractFundsButton = new Button("Subtract from funds");
    	subtractFundsButton.setOnAction(e -> applicationStage.setScene(subtractFundsScene));
    	Button expensesButton = new Button("Calculate monthly expenses");
    	Button exitButton = new Button("Exit");
    	exitButton.setOnAction(exitApp -> System.exit(0));
    	mainMenuContainer.getChildren().addAll(mainMenuTitle, availFunds, addFundsButton, subtractFundsButton, expensesButton, exitButton);
    	applicationStage.setScene(mainMenuScene);
    	
    	// add funds widgets
    	Label addFundsTitle = new Label("Add Funds");
    	//Label availFundsAdd = new Label("Available funds: $" + funds);
    	TextField toAdd = new TextField();
    	Button addButton = new Button("Add");
    	addButton.setOnAction(addEvent -> addFunds(mainMenuScene, toAdd)); // calls addFunds method
    	Button cancelAdd = new Button("Cancel");
    	cancelAdd.setOnAction(cancelEvent -> cancelAction(mainMenuScene, toAdd));
    	addFundsContainer.getChildren().addAll(addFundsTitle, availFunds, toAdd, addButton, cancelAdd);
    	
    	// subtract funds widgets
    	Label subtractFundsTitle = new Label("Subtract Funds");
    	//Label availFundsSub = new Label("Available funds: $" + funds);
    	TextField toSubtract = new TextField();
    	Button subButton = new Button("Subtract");
    	subButton.setOnAction(subEvent -> subtractFunds(mainMenuScene, toSubtract));
    	Button cancelSub = new Button("Cancel");
    	cancelSub.setOnAction(cancelEvent -> cancelAction(mainMenuScene, toSubtract));
    	subtractFundsContainer.getChildren().addAll(subtractFundsTitle, availFunds, toSubtract, subButton, cancelSub);
    }

	void addFunds(Scene mainMenuScene, TextField toAdd) {
		applicationStage.setScene(mainMenuScene);
    	double toAddDouble = Double.parseDouble(toAdd.getText());
    	toAdd.clear();
    	
    	funds = funds + toAddDouble; // why doesn't this change funds for label on its own?
    	availFunds.setText("Available funds: $" + funds); // (without this)
    }
	
	void subtractFunds(Scene mainMenuScene, TextField toSubtract) {
		applicationStage.setScene(mainMenuScene);
		double toSubDouble = Double.parseDouble(toSubtract.getText());
		
		toSubtract.clear();
		funds = funds - toSubDouble;
		availFunds.setText("Available funds: $" + funds);
	}
	
	/*public boolean inputChecker(double input) {
    	if (input > 0) {
    		validInputCheck = true;
    	} else if (input == 0) {
    		validInputCheck = false;
    		errorMessage.setText("Please enter an amount.");
    	} else {
    		validInputCheck = false;
    		errorMessage.setText("Invalid input");
    	}
    	
    	return validInputCheck;
	}*/
	
	void cancelAction(Scene mainMenuScene, TextField inputTextField) {
		applicationStage.setScene(mainMenuScene);
		inputTextField.clear();
	}
    
}