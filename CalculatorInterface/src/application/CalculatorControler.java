package application;

import java.awt.Toolkit;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class CalculatorControler implements CalculatorControlerInterface {
	
	private CalculatorModel calculator;
	private CalculatorGUI GUI;
	
	public CalculatorControler(CalculatorModel calculator,CalculatorGUI GUI) {
		this.calculator=calculator;
		this.GUI = GUI;
	}
	@Override
	public void change(String accu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void change(List<Double> stackData) {
		
		
	}
	
	public void performOperation(String operation,ListView<String> stackView) {
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
            GUI.ErrorMessage(e.getMessage());
        }
	}
	
	void push(ListView<String> stackView,TextField inputField) {
		String strvalue = inputField.getText();
        try {
        	double value = Double.parseDouble(strvalue);
            calculator.push(value);
            stackView.getItems().add(0, String.valueOf(value));
            inputField.clear();
        } catch (Exception e) {
        	Toolkit.getDefaultToolkit().beep();
        }
	}
	void remove(ListView<String> stackView) {
		try {
			calculator.drop();
			// Assuming originalListView is your original ListView
			ObservableList<String> originalItems = stackView.getItems();

			// Create a new ObservableList from the original list minus its last item
			ObservableList<String> newItems = FXCollections.observableArrayList(originalItems.subList(1, originalItems.size()));

			// Create a new ListView with the new items
			stackView.setItems(newItems);
		}catch(RuntimeException e) {
			GUI.ErrorMessage(e.getMessage());
		}
	}
	//////////////// Ajout des graphes

    public void plotGraph(List<Double> xData, List<Double> yData) {
        // Ensure data is valid:
        if (xData.size() != yData.size()) {
            GUI.showError("Mismatched x and y data sizes.");
            return;
        }
        
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < xData.size(); i++) {
            series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
        }
        
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");
        
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().add(series);
        GUI.displayGraph(lineChart);
    }
}
