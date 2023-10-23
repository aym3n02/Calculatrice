package application;

import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;



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
	
	void push(ListView<String> stackView,double value) {
        try {
            calculator.push(value);
            stackView.getItems().add(0, String.valueOf(value));
        } catch (NumberFormatException e) {
            GUI.ErrorMessage("entrée invalide");
        }
	}
	
	//////////////// Ajout des graphes
	public void plotGraph(double a, double b) {
        // Define the x and y axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        // Create a line chart using the defined x and y axes
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Create a data series for the function y = ax + b
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (double x = -10; x <= 10; x += 0.1) { // This is an arbitrary range, adjust as needed.
            series.getData().add(new XYChart.Data<>(x, a * x + b));
        }

        // Add the data series to the line chart
        lineChart.getData().add(series);
        
        // Display the graph using the GUI class
        GUI.displayGraph(lineChart);
    }

}
