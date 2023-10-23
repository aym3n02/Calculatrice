module CalculatorInterface {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.scripting;
	
	opens application to javafx.graphics, javafx.fxml;
}
