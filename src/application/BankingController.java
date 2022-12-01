package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingController {
	Stage applicationStage;
	
    @FXML
    private TextField fundsTextfield;

    @FXML
    void mainMenu(ActionEvent event) { // first "Next" button
    	VBox mainMenuContainer = new VBox();
    	Scene mainMenuScene = new Scene(mainMenuContainer);
    	double funds = Double.parseDouble(fundsTextfield.getText());
    	
    	Label mainMenuTitle = new Label("Main Menu");
    	Label availFunds = new Label("Available funds: $" + fundsTextfield.getText());
    	Button addFundsButton = new Button("Add to funds");
    	Button subtractFundsButton = new Button("Subtract from funds");
    	Button expensesButton = new Button("Calculate monthly expenses");
    	Button exitButton = new Button("Exit");
    	mainMenuContainer.getChildren().addAll(mainMenuTitle, availFunds, addFundsButton, subtractFundsButton, expensesButton, exitButton);
    	applicationStage.setScene(mainMenuScene);
    	
    	if (funds > 0) {
    		System.out.println("Current funds: " + funds);
    	} else if (funds == 0) {
    		System.out.println("Please enter an amount");
    	} else {
    		System.out.println("Invalid input");
    	}
    }
}