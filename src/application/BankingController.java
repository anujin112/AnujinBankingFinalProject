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
	
    private Label availFunds = new Label();
    private double funds;
	
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
    	availFunds.setText("Available funds: $" + funds);
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
    	Label availFundsAdd = new Label("Available funds: $" + funds);
    	TextField toAdd = new TextField();
    	Button addButton = new Button("Add");
    	addButton.setOnAction(addEvent -> addFunds(mainMenuScene, toAdd));
    	Button cancelAdd = new Button("Cancel");
    	cancelAdd.setOnAction(cancelEvent -> cancelAction(mainMenuScene));
    	addFundsContainer.getChildren().addAll(addFundsTitle, availFundsAdd, toAdd, addButton, cancelAdd);
    	
    	// subtract funds widgets
    	Label subtractFundsTitle = new Label("Subtract Funds");
    	TextField toSub = new TextField();
    	Button subButton = new Button("Subtract");
    	subtractFundsContainer.getChildren().addAll(subtractFundsTitle, toSub, subButton);
    	
    	// first draft error handling
    	/*if (funds > 0) {
    		System.out.println("Current funds: " + funds);
    	} else if (funds == 0) {
    		System.out.println("Please enter an amount");
    	} else {
    		System.out.println("Invalid input");
    	}*/
    }

	void addFunds(Scene mainMenuScene, TextField toAdd) {
    	applicationStage.setScene(mainMenuScene);
    	double toAddDouble = Double.parseDouble(toAdd.getText());
    	
    	toAdd.clear();
    	funds = funds + toAddDouble; // why doesn't this change funds for label on its own?
    	availFunds.setText("Available funds: $" + funds);
    }
	
	void cancelAction(Scene mainMenuScene) {
		applicationStage.setScene(mainMenuScene);
	}
    
}