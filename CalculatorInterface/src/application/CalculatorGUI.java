package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

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
        VBox topLayout = new VBox();
        
        /// Ajout du de la barre de menu
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Graph");
        MenuItem importItem = new MenuItem("Import cloud");
        fileMenu.getItems().add(importItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);
        importItem.setOnAction(event -> chooseAndPlotCSV());
        

        // Setup buttons
        inputField.setOnAction(event -> controler.push(stackView, inputField));
        Button pushButton = new Button("Push");
        pushButton.setOnAction(event -> {
        
            // Fire the action event of inputField
            ActionEvent actionEvent = new ActionEvent(inputField, null);
            inputField.fireEvent(actionEvent);
        });
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event ->controler.remove(stackView));
        Button addButton = new Button("+");
        addButton.setOnAction(event -> controler.performOperation("add",stackView));
        Button subButton = new Button("-");
        subButton.setOnAction(event -> controler.performOperation("sub",stackView));
        Button mulButton = new Button("*");
        mulButton.setOnAction(event -> controler.performOperation("mul",stackView));
        Button divButton = new Button("/");
        divButton.setOnAction(event -> controler.performOperation("div",stackView));

        // Add buttons
        buttons.getChildren().addAll(pushButton,removeButton, addButton, subButton, mulButton, divButton);
        root.setCenter(stackView);
        root.setBottom(buttons);
        topLayout.getChildren().addAll(menuBar, inputField);
        root.setTop(topLayout);

        // Initialize the scene
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public void displayGraph(LineChart<Number, Number> chart) {
        BorderPane graphPane = new BorderPane();
        graphPane.setCenter(chart);
        
        Stage graphStage = new Stage();
        graphStage.setTitle("Graph");
        graphStage.setScene(new Scene(graphPane, 400, 300));
        graphStage.show();
    }
    
    ////////// file explorer

    private void chooseAndPlotCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("import point cloud");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) { // if null, no file was selected
            List<Double> xData = new ArrayList<>();
            List<Double> yData = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    xData.add(Double.parseDouble(values[0].trim()));
                    yData.add(Double.parseDouble(values[1].trim()));
                }
                controler.plotGraph(xData, yData); 
                
            } catch (IOException e) {
                showError("Error reading the CSV file.");
            } catch (NumberFormatException e) {
                showError("Invalid data format in CSV.");
            }
        }
    }
}
