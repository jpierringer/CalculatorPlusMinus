package calcplusminus;

public class CalcPlusMinusMain {

    public static void main(String[] args) throws Exception {
        CalcPlusMinus calc = new CalcPlusMinus();
        
        calc.parseExpression("1 + 1 + 1 - 2 + 10");
        System.out.println("Pre-eval: " + calc.toString());
        System.out.println("Result: " + calc.evaluate());
        System.out.println("Post-eval: " + calc.toString());
        
        calc.parseExpression("1 + 1 + 1 - 2 + 12");
        System.out.println("Post-eval (dirty): " + calc.toString());
        
        calc.evaluate();
        System.out.println("Post-reeval: " + calc.toString());
    }
}
