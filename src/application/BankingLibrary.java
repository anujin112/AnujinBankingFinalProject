package application;

import javafx.scene.control.TextField;

public class BankingLibrary {
	double funds;
	
	double setInputValue(TextField inputTextField) {
		double inputAsDouble = Double.parseDouble(inputTextField.getText());
		return inputAsDouble;
	}
}
