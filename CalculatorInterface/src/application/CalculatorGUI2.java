package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.List;

/*
public class CalculatorGUI2 extends Application implements CalculatorGUInterface {

    private Calculator calculator = new Calculator();
    private TextField inputField = new TextField();
    private ListView<String> stackView = new ListView<>();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

        buttons.getChildren().addAll(pushButton, addButton, subButton, mulButton, divButton);
        root.setCenter(stackView);
        root.setBottom(buttons);
        root.setTop(inputField);

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Stack Calculator");
        primaryStage.setScene(scene);
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
            double result = calculator.peek();
            stackView.getItems().remove(0);
            stackView.getItems().set(0, String.valueOf(result));
        } catch (RuntimeException e) {
            inputField.setText(e.getMessage());
        }
        
    }

    @Override
    public void affiche() {
        primaryStage.show();
    }

    @Override
    public void change(String acc) {
        inputField.setText(acc);
    }

    @Override
    public void change(List<Double> stackData) {
        stackView.getItems().clear();
        for (Double data : stackData) {
            stackView.getItems().add(String.valueOf(data));
        }
    }
    
}
*/

public class CalculatorGUI2 extends Application implements CalculatorGUInterface {

	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void change(String acc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void change(List<Double> stackData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}

