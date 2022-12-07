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

/**
 * 
 * @author Anujin
 *
 */
public class BankingController {
	Stage applicationStage;
    private double funds;
    private Label errorMessage = new Label("");
    private Label availFunds = new Label("Available funds: $" + funds);
    private List<Label> availFundsLabels = new ArrayList<Label>();
    private Label totalExpensesLabel = new Label("");
    private Label expensesResultLabel = new Label("");
    private boolean validInputCheck = true;
	
    @FXML
    private TextField fundsTextfield;
    
    /**
     * Sets up the main menu and all relevant widgets in advance 
     * of any user input or calculations.
     * @param event
     */
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
    	mainMenuContainer.getChildren().addAll(mainMenuTitle, availFunds, addFundsButton, subtractFundsButton, expensesButton, errorMessage, exitButton);
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
    	calcExpensesButton.setOnAction(calcExpensesevent -> {
			try {
				calculateExpenses(mainMenuScene, budgetTextField, billsTextField, groceriesTextField, subscribsTextField);
			} catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
		});
    	calcExpensesContainer.getChildren().addAll(calcExpensesTitle, promptBudget, budgetTextField, promptBills, billsTextField, promptGroceries, groceriesTextField, promptSubscribs, subscribsTextField, calcExpensesButton, totalExpensesLabel, expensesResultLabel);	
    }
    
    /**
     * Adds the double value entered by the user to the total
     * available funds that was initially given. Updates
     * the available funds, then takes the user back to
     * the main menu.
     * @param mainMenuScene
     * @param toAdd
     */
	void addFunds(Scene mainMenuScene, TextField toAdd) {
		applicationStage.setScene(mainMenuScene);
		errorMessage.setText("");
		String toAddString = toAdd.getText();
		
		for (char c : toAddString.toCharArray()) {
			if (!Character.isDigit(c)) {
				validInputCheck = false;
				errorMessage.setText("Invalid input");
			}
		}
		
		double toAddDouble = 0.0;
		if (validInputCheck) {
			toAddDouble = convertToDouble(toAdd);
			
			if (toAddDouble > 0) {
		    	toAdd.clear();
		    	funds = funds + toAddDouble;
		    	labelsRefresher(funds);
	    	} else if (toAddDouble == 0) {
	    		errorMessage.setText("Please enter an amount.");
	    	} else {
	    		errorMessage.setText("Invalid input");
	    	}
		} else {
			errorMessage.setText("Invalid input");
		}
		
		toAdd.clear();
    }
	
	/**
     * Subtracts the double value entered by the user from the total
     * available funds that was initially given. Updates
     * the available funds, then takes the user back to
     * the main menu.
     * @param mainMenuScene
     * @param toSubtract
     */
	void subtractFunds(Scene mainMenuScene, TextField toSubtract) {
		applicationStage.setScene(mainMenuScene);
		errorMessage.setText("");
		String toSubtractString = toSubtract.getText();

		for (char c : toSubtractString.toCharArray()) {
			if (!Character.isDigit(c)) {
				validInputCheck = false;
				errorMessage.setText("Invalid input");
			}
		}
		
		double toSubtractDouble = 0.0;
		if (validInputCheck) {
			toSubtractDouble = convertToDouble(toSubtract);
			
			if (toSubtractDouble > 0) {
		    	toSubtract.clear();
		    	funds = funds + toSubtractDouble;
		    	labelsRefresher(funds);
	    	} else if (toSubtractDouble == 0) {
	    		errorMessage.setText("Please enter an amount.");
	    	} else {
	    		errorMessage.setText("Invalid input");
	    	}
		} else {
			errorMessage.setText("Invalid input");
		}
		
		toSubtract.clear();
	}
	
	/**
	 * Uses information entered by the user for their month's expenses
	 * to determine whether the user stayed on budget or not by 
	 * comparing the sum of the expenses to a budget given by the user.
	 * @param mainMenuScene
	 * @param budgetTextField
	 * @param billsTextField
	 * @param groceriesTextField
	 * @param subscribsTextField
	 * @throws InvalidInputException 
	 */
	void calculateExpenses(Scene mainMenuScene, TextField budgetTextField, TextField billsTextField, TextField groceriesTextField, TextField subscribsTextField) throws InvalidInputException {
		ArrayList<TextField> allTextFields = new ArrayList<TextField>();
		Collections.addAll(allTextFields, budgetTextField, billsTextField, groceriesTextField, subscribsTextField);
		double budget = convertToDouble(budgetTextField);
		double bills = convertToDouble(billsTextField);
		double groceries = convertToDouble(groceriesTextField);
		double subscriptions = convertToDouble(subscribsTextField);
		double totalExpenses = bills + groceries + subscriptions;
		
		if (validInputCheck == true) {
			if (budget < 0 || bills < 0 || groceries < 0 || subscriptions < 0) {
				validInputCheck = false;
				errorMessage.setText("Invalid input");
				applicationStage.setScene(mainMenuScene);
			}
			
			if (validInputCheck) {
				if (totalExpenses <= budget) {
					expensesResultLabel.setText("You stayed on budget!");
				} else {
					expensesResultLabel.setText("You went over budget!");
				}
				
				totalExpensesLabel.setText("Your total expenses: $" + totalExpenses);
			}
			
			// clearing all text fields
			for (int index = 0; index < allTextFields.size(); index++) {
				allTextFields.get(index).clear();
			}
		}
	}
	
	/**
	 * Converts a given TextField's text to a double
	 * to be used for calculations. Returns the double.
	 * @param textFieldInput
	 * @return
	 * @throws InvalidInputException 
	 */
	public double convertToDouble(TextField textFieldInput) {
		double inputAsDouble = 0.0;
		
		try {
			if (!textFieldInput.getText().isBlank()) {
				inputAsDouble = Double.parseDouble(textFieldInput.getText());
			} else {
				validInputCheck = false;
				throw new InvalidInputException();
			}
		} catch (InvalidInputException iie) {
			expensesResultLabel.setText("Enter 0 if there were no expenses");
		}
		
		return inputAsDouble;
	}
	
	/**
	 * Refreshes the available funds label that appears
	 * at the top of most scenes, ensuring the value stays consistent
	 * and up to date.
	 * @param funds
	 */
	void labelsRefresher(double funds) {
		for (int index = 0; index < availFundsLabels.size(); index++) {
			availFundsLabels.get(index).setText("Available funds: $" + Double.toString(funds));
		}
	}
	
	/**
	 * Clears any inputs that may have bee entered
	 * by the user before taking the user back to
	 * the main menu.
	 * @param mainMenuScene
	 * @param inputTextField
	 */
	void cancelAction(Scene mainMenuScene, TextField inputTextField) {
		applicationStage.setScene(mainMenuScene);
		inputTextField.clear();
	}
}