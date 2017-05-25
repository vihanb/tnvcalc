import java.math.*;
import tokenizer.*;
import java.util.Stack;

public class Evaluator {
    private Stack<Token> tokens;
    public Evaluator(Stack<Token> tokens) {
        this.tokens = tokens;
    }

    public String withBigDecimal() {
        BigDecimal result = BigDecimal.ZERO;
        Stack<BigDecimal> numbers = new Stack<BigDecimal>();

        while(!this.tokens.empty()) {
            Token t = this.tokens.remove(0);

            switch (t.getType()) {
                case NUMBER: numbers.push(new BigDecimal(t.getValue())); break;
                case OPERATOR:
                             BigDecimal b = numbers.pop(); // RHS
                             BigDecimal a = numbers.pop(); // LHS
                             BigDecimal c; // Result
                             switch (t.getValue()) {
                                case "+": c = a.add(b); break;
                                case "-": c = a.subtract(b); break;
                                case "*": c = a.multiply(b); break;
                                case "/": c = a.divide(b); break;
                                case "^": c = a.pow(b.intValue()); break;
                                default: throw new RuntimeException("Unknown operator `" + t.getValue() + "`");
                             }
                             numbers.push(c);
                             break;
                default: throw new RuntimeException("Malformed item in evaluation stack");
            }
        }

        return numbers.pop().toPlainString();
    }

    public String withRational() {
        // TODO: Evaluate with rationals
        return null;
    }
}
