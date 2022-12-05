package application;

import java.util.ArrayList;
import java.util.Collections;
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
    private Label availFunds = new Label("Available funds: $" + funds);
    private List<Label> availFundsLabels = new ArrayList<Label>();
    private Label totalExpensesLabel = new Label("");
    private Label expensesResultLabel = new Label("");
	
    @FXML
    private TextField fundsTextfield;
    
    @FXML
    void mainMenuSetAll(ActionEvent event) { // first "Next" button
    	VBox mainMenuContainer = new VBox();
    	VBox addFundsContainer = new VBox();
    	VBox subtractFundsContainer = new VBox();
    	VBox calcExpensesContainer = new VBox();
    	Scene mainMenuScene = new Scene(mainMenuContainer);
    	Scene addFundsScene = new Scene(addFundsContainer);
    	Scene subtractFundsScene = new Scene(subtractFundsContainer);
    	Scene calcExpensesScene = new Scene(calcExpensesContainer);
    	funds = Double.parseDouble(fundsTextfield.getText());
    	availFundsLabels.add(availFunds);
    	
    	// main menu widgets
    	Label mainMenuTitle = new Label("Main Menu");
    	availFunds.setText("Available funds: $" + funds);
    	Button addFundsButton = new Button("Add to funds");
    	addFundsButton.setOnAction(e -> applicationStage.setScene(addFundsScene));
    	Button subtractFundsButton = new Button("Subtract from funds");
    	subtractFundsButton.setOnAction(e -> applicationStage.setScene(subtractFundsScene));
    	Button expensesButton = new Button("Calculate monthly expenses");
    	expensesButton.setOnAction(e -> applicationStage.setScene(calcExpensesScene));
    	Button exitButton = new Button("Exit");
    	exitButton.setOnAction(exitApp -> System.exit(0));
    	mainMenuContainer.getChildren().addAll(mainMenuTitle, availFunds, addFundsButton, subtractFundsButton, expensesButton, exitButton);
    	applicationStage.setScene(mainMenuScene);
    	
    	// add funds widgets
    	Label addFundsTitle = new Label("Add Funds");
    	Label availFundsAdd = new Label("Available funds: $" + funds);
    	availFundsLabels.add(availFundsAdd);
    	TextField toAdd = new TextField();
    	Button addButton = new Button("Add");
    	addButton.setOnAction(addEvent -> addFunds(mainMenuScene, toAdd)); // calls addFunds method
    	Button cancelAdd = new Button("Cancel");
    	cancelAdd.setOnAction(cancelEvent -> cancelAction(mainMenuScene, toAdd));
    	addFundsContainer.getChildren().addAll(addFundsTitle, availFundsAdd, toAdd, addButton, cancelAdd);
    	
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
    
    	// calculate expenses widgets
    	Label calcExpensesTitle = new Label("Calculate Monthly Expenses");
    	Label promptBudget = new Label("What was your budget for the month?");
    	TextField budgetTextField = new TextField();
    	Label promptBills = new Label("How much did you spend on bills?");
    	TextField billsTextField = new TextField();
    	Label promptGroceries = new Label("How much did you spend on groceries?");
    	TextField groceriesTextField = new TextField();
    	Label promptSubscribs = new Label("How much did you spend on subscriptions?");
    	TextField subscribsTextField = new TextField();
    	Button calcExpensesButton = new Button("Calculate");
    	Button backButton = new Button("Back");
    	backButton.setOnAction(backEvent -> applicationStage.setScene(mainMenuScene));
    	calcExpensesButton.setOnAction(calcExpensesevent -> calculateExpenses(mainMenuScene, budgetTextField, billsTextField, groceriesTextField, subscribsTextField));
    	calcExpensesContainer.getChildren().addAll(calcExpensesTitle, promptBudget, budgetTextField, promptBills, billsTextField, promptGroceries, groceriesTextField, promptSubscribs, subscribsTextField, calcExpensesButton, totalExpensesLabel, expensesResultLabel);
    	
    }

	void addFunds(Scene mainMenuScene, TextField toAdd) {
		applicationStage.setScene(mainMenuScene);
		Banking adding = new Banking();
		double toAddDouble = adding.convertToDouble(toAdd);
		
    	toAdd.clear();
    	funds = funds + toAddDouble;
    	labelsRefresher(funds);
    	//availFunds.setText("Available funds: $" + funds);
    }
	
	void subtractFunds(Scene mainMenuScene, TextField toSubtract) {
		applicationStage.setScene(mainMenuScene);
		Banking subtracting = new Banking();
		double toSubDouble = subtracting.convertToDouble(toSubtract);
		
		toSubtract.clear();
		funds = funds - toSubDouble;
		labelsRefresher(funds);
		//availFunds.setText("Available funds: $" + funds);
	}
	
	void calculateExpenses(Scene mainMenuScene, TextField budgetTextField, TextField billsTextField, TextField groceriesTextField, TextField subscribsTextField) {
		ArrayList<TextField> allTextFields = new ArrayList<TextField>();
		List<Double> expensesDoubles = new ArrayList<Double>();
		Collections.addAll(allTextFields, billsTextField, groceriesTextField, subscribsTextField);
		Banking expenses = new Banking();
		double budget = expenses.convertToDouble(budgetTextField);
		double totalExpenses = 0.0;
		
		// clearing all text fields
		for (int index = 0; index < allTextFields.size(); index++) {
			allTextFields.get(index).clear();
		}
		
		// converting doubles
		for (int index = 0; index < expensesDoubles.size(); index ++) {
			Collections.addAll(expensesDoubles, (expenses.convertToDouble(allTextFields.get(index))));
		}
		
		// calculating total expenses using converted doubles list
		for (double x : expensesDoubles) {
			totalExpenses = totalExpenses + x; // not being updated
		}
		
		if (totalExpenses <= budget) {
			expensesResultLabel.setText("You stayed on budget!");
		} else {
			expensesResultLabel.setText("You went over budget!");
		}
		
		totalExpensesLabel.setText("Your total expenses: $" + totalExpenses);
	}
	
	// put these in a separate class
	public double convertToDouble(TextField textFieldInput) {
		double inputAsDouble = Double.parseDouble(textFieldInput.getText());
		return inputAsDouble;
	}
	
	void labelsRefresher(double funds) {
		for (int index = 0; index < availFundsLabels.size(); index++) {
			availFundsLabels.get(index).setText("Available funds: $" + Double.toString(funds));
		}
	}
	
	void cancelAction(Scene mainMenuScene, TextField inputTextField) {
		applicationStage.setScene(mainMenuScene);
		inputTextField.clear();
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
	
}