package operator;

public class Main {

    public static void main(String[] args) {
    	Calculator calc = new Calculator();
        
        calc.push(5);
        calc.push(3);
        calc.addition();
        System.out.println(calc.peek());  // Devrait afficher 8.0
    }
}
