package application;

import java.util.List;

import javafx.scene.control.TextField;

public class Banking {
	double inputAsDouble;
	double totalExpenses;
	
	public double convertToDouble(TextField textFieldInput) {
		inputAsDouble = Double.parseDouble(textFieldInput.getText());
		return inputAsDouble;
	}
	
}
