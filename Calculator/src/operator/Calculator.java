package operator;
import java.util.Stack;

public class Calculator {

    private Stack<Double> pile;

    public Calculator() {
        this.pile = new Stack<>();
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

    public void addition() {
        verifierTaillePile(2);
        double b = pop();
        double a = pop();
        pile.push(a + b);
    }

    public void soustraction() {
        verifierTaillePile(2);
        double b = pop();
        double a = pop();
        pile.push(a - b);
    }

    public void multiplication() {
        verifierTaillePile(2);
        double b = pop();
        double a = pop();
        pile.push(a * b);
    }

    public void division() {
        verifierTaillePile(2);
        double b = pop();
        if (b == 0) {
            throw new ArithmeticException("Division par zéro");
        }
        double a = pop();
        pile.push(a / b);
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