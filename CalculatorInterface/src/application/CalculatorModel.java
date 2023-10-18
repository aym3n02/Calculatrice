package application;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface{

	private String accu;
    private Stack<Double> pile;

    public CalculatorModel() {
        this.pile = new Stack<>();
        accu="";
    }

    // Opérations de base

    public void push(double valeur) {
        pile.push(valeur);
    }

    public double pop() {
        if (!pile.isEmpty()) {
            return pile.pop();
        }
        throw new RuntimeException("Pile vide");
    }

    public void add() {
        verifierTaillePile(2);
        double b = pop();
        double a = pop();
        pile.push(a + b);
    }

    public void substract() {
        verifierTaillePile(2);
        double b = pop();
        double a = pop();
        pile.push(a - b);
    }

    public void multiply() {
        verifierTaillePile(2);
        double b = pop();
        double a = pop();
        pile.push(a * b);
    }

    public void divide() {
        verifierTaillePile(2);
        double b = pop();
        if (b == 0) {
            throw new ArithmeticException("Division par zéro");
        }
        double a = pop();
        pile.push(a / b);
    }
    
    public void opposite() {
    	verifierTaillePile(1);
    	double a =this.pile.pop();
    	this.pile.push(a);
    }
    
    public void drop() {
    	verifierTaillePile(1);
    	this.pile.pop();
    }
    public void swap() {
    	verifierTaillePile(2);
    	double a = this.pop();
    	double b = this.pop();
    	
    	this.push(a);
    	this.push(b);
    }
    public void clear() {
    	this.pile = new Stack<>();
    }


    public double peek() {
        return pile.peek();
    }

    private void verifierTaillePile(int tailleRequise) {
        if (pile.size() < tailleRequise) {
            throw new RuntimeException("Pas assez d'opérandes sur la pile pour l'opération.");
        }
    }

}