package application;

import java.util.ArrayList;
import java.util.List;
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
    private TextField userInput;
    private Label availFunds = new Label("Available funds: $" + funds);
    private List<Label> availFundsLabels = new ArrayList<Label>();
	
    @FXML
    private TextField fundsTextfield;
    
    @FXML
    void mainMenu(ActionEvent event) { // first "Next" button
    	VBox mainMenuContainer = new VBox();
    	VBox addFundsContainer = new VBox();
    	VBox subtractFundsContainer = new VBox();
    	Scene mainMenuScene = new Scene(mainMenuContainer);
    	Scene addFundsScene = new Scene(addFundsContainer);
    	sceneSetter(addFundsScene, mainMenuScene, addFundsContainer);
    	Scene subtractFundsScene = new Scene(subtractFundsContainer);
    	funds = Double.parseDouble(fundsTextfield.getText());
    	availFundsLabels.add(availFunds);
    	
    	// main menu widgets
    	Label mainMenuTitle = new Label("Main Menu");
    	availFunds.setText("Available funds: $" + funds);
    	Button addFundsButton = new Button("Add to funds");
    	addFundsButton.setOnAction(addEvent -> addFunds(mainMenuScene, userInput));
    	Button subtractFundsButton = new Button("Subtract from funds");
    	subtractFundsButton.setOnAction(e -> applicationStage.setScene(subtractFundsScene));
    	Button expensesButton = new Button("Calculate monthly expenses");
    	Button exitButton = new Button("Exit");
    	exitButton.setOnAction(exitApp -> System.exit(0));
    	mainMenuContainer.getChildren().addAll(mainMenuTitle, availFunds, addFundsButton, subtractFundsButton, expensesButton, exitButton);
    	applicationStage.setScene(mainMenuScene);
    	
    	// subtract funds widgets
    	Label subtractFundsTitle = new Label("Subtract Funds");
    	Label availFundsSub = new Label("Available funds: $" + funds);
    	availFundsLabels.add(availFundsSub);
    	TextField toSubtract = new TextField();
    	Button subButton = new Button("Subtract");
    	subButton.setOnAction(subEvent -> subtractFunds(mainMenuScene, toSubtract));
    	Button cancelSub = new Button("Cancel");
    	cancelSub.setOnAction(cancelEvent -> cancelAction(mainMenuScene, toSubtract));
    	subtractFundsContainer.getChildren().addAll(subtractFundsTitle, availFundsSub, toSubtract, subButton, cancelSub);
    }

    void sceneSetter(Scene scene, Scene mainMenuScene, VBox container) {
    	
    	// add funds widgets
    	Label addFundsTitle = new Label("Add Funds");
    	Label availFundsAdd = new Label("Available funds: $" + funds);
    	availFundsLabels.add(availFundsAdd);
    	Button addButton = new Button("Add");
    	Button cancelAdd = new Button("Cancel");
    	cancelAdd.setOnAction(cancelEvent -> cancelAction(mainMenuScene, userInput));
    	container.getChildren().addAll(addFundsTitle, availFundsAdd, userInput, addButton, cancelAdd);
    	applicationStage.setScene(scene);
    }
    
	void addFunds(Scene mainMenuScene, TextField toAdd) {
		applicationStage.setScene(mainMenuScene);
		double toAddDouble = Double.parseDouble(toAdd.getText());
		
    	toAdd.clear();
    	funds = funds + toAddDouble;
    	labelsRefresher(funds);
    }
	
	void subtractFunds(Scene mainMenuScene, TextField toSubtract) {
		applicationStage.setScene(mainMenuScene);
		double toSubDouble = Double.parseDouble(toSubtract.getText());
		
		toSubtract.clear();
		funds = funds - toSubDouble;
		labelsRefresher(funds);
	}
	
	void labelsRefresher(double funds) {
		for (int index = 0; index < availFundsLabels.size(); index++) {
			availFundsLabels.get(index).setText("Available funds: $" + Double.toString(funds));
		}
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