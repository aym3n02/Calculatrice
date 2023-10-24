module CalculatorInterface {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.scripting;
	requires javafx.base;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
