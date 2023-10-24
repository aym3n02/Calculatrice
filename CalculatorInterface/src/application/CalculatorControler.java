package application;

import java.awt.Toolkit;
import java.util.List;

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
