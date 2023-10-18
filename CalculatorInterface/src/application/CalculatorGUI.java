package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CalculatorGUI extends Application {

    private CalculatorModel calculator = new CalculatorModel();
    private TextField inputField = new TextField();
    private ListView<String> stackView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox buttons = new HBox(5);

        // Setup buttons
        Button pushButton = new Button("Push");
        pushButton.setOnAction(event -> pushToStack());
        Button addButton = new Button("+");
        addButton.setOnAction(event -> performOperation("add"));
        Button subButton = new Button("-");
        subButton.setOnAction(event -> performOperation("sub"));
        Button mulButton = new Button("*");
        mulButton.setOnAction(event -> performOperation("mul"));
        Button divButton = new Button("/");
        divButton.setOnAction(event -> performOperation("div"));

        // Add buttons
        buttons.getChildren().addAll(pushButton, addButton, subButton, mulButton, divButton);
        root.setCenter(stackView);
        root.setBottom(buttons);
        root.setTop(inputField);

        // Initialize the scene
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void pushToStack() {
        try {
            double value = Double.parseDouble(inputField.getText());
            calculator.push(value);
            stackView.getItems().add(0, String.valueOf(value));
            inputField.clear();
        } catch (NumberFormatException e) {
            inputField.setText("entrée invalide.");
        }
    }
    
    private void performOperation(String operation) {
        try {
            switch (operation) {
                case "add":
                    calculator.add();
                    break;
                case "sub":
                    calculator.substract();
                    break;
                case "mul":
                    calculator.multiply();
                    break;
                case "div":
                    calculator.divide();
                    break;
            }
            // Update the list view to reflect the changes in the stack
            double result = calculator.peek();
            stackView.getItems().remove(0);  // Remove second number
            stackView.getItems().set(0, String.valueOf(result));  // Replace first number with result
        } catch (RuntimeException e) {
            inputField.setText(e.getMessage());
        }
    }


}
