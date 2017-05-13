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

        while(!this.tokens.empty()) {
            Token t = this.tokens.pop();
            if (t.getType() != TokenType.OPERATOR) throw new RuntimeException("Arity Mismatch");
            switch (t.getValue()) {
                default: throw new RuntimeException("Unhandled operator `" + t.getValue() + "`");
            }

        }

        return result.toPlainString();
    }

    public String withRational() {
        // TODO: Evaluate with rationals
        return null;
    }
}
