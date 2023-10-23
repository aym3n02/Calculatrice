package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CalculatorGUI extends Application {

    private CalculatorControler controler;
    
    private TextField inputField = new TextField();
    private ListView<String> stackView = new ListView<>();
    
    public CalculatorGUI() {
    	this.controler = new CalculatorControler(new CalculatorModel(),this);
    }
    public CalculatorGUI(CalculatorControler controler) {
    	this.controler = controler;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox buttons = new HBox(5);
        
        /// Ajouts boutons pour le graphe 
        Button plotButton = new Button("Plot");
        plotButton.setOnAction(event -> {
            double a = 1;
            double b = 0;
            controler.plotGraph(a, b);
        });

        buttons.getChildren().add(plotButton);
        //////////////

        // Setup buttons
        Button pushButton = new Button("Push");
        pushButton.setOnAction(event -> pushToStack());
        Button addButton = new Button("+");
        addButton.setOnAction(event -> controler.performOperation("add",stackView));
        Button subButton = new Button("-");
        subButton.setOnAction(event -> controler.performOperation("sub",stackView));
        Button mulButton = new Button("*");
        mulButton.setOnAction(event -> controler.performOperation("mul",stackView));
        Button divButton = new Button("/");
        divButton.setOnAction(event -> controler.performOperation("div",stackView));

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
    	double value = Double.parseDouble(inputField.getText());
    	controler.push(stackView, value);
 
        inputField.clear();
    }
    
    public void showError(String error) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        //alert.setHeaderText("An Error Occurred");
        alert.setContentText(error);
        alert.showAndWait();
    }



    public void ErrorMessage(String message) {
    	//inputField.setText(message);
    	showError(message);
    }
    
    
    /////////////// Ajout graphe
    private CalculatorModel model;
    private CalculatorGUI gui;

    // ... autres méthodes et constructeurs ...

    public void plotGraph(double a, double b) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (double x = -10; x <= 10; x += 0.1) {
            series.getData().add(new XYChart.Data<>(x, a * x + b));
        }

        lineChart.getData().add(series);

        gui.displayGraph(lineChart);
    }
    public void displayGraph(LineChart<Number, Number> chart) {
        BorderPane graphPane = new BorderPane();
        graphPane.setCenter(chart);
        
        Stage graphStage = new Stage();
        graphStage.setTitle("Graph");
        graphStage.setScene(new Scene(graphPane, 400, 300));
        graphStage.show();
    }

}
