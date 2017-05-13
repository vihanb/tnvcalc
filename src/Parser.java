import tokenizer.*;
import java.util.Stack;

public class Parser {
    public Stack<Token> parse(Stack<Token> tokens) {
        Stack<Token> output = new Stack<Token>();
        Stack<Token> operatorStack = new Stack<Token>();

        while (!tokens.empty()) {
            Token token = tokens.remove(0);

            if (token.getType() == TokenType.NUMBER) output.push(token);
            if (token.getType() == TokenType.OPERATOR) {
                int precedence = TokenType.getPrecedence(token.getValue());

                while (!operatorStack.empty()) {
                    Token o2 = operatorStack.peek();
                    if (o2.getType() != TokenType.OPERATOR) break;

                    int o2prec = TokenType.getPrecedence(o2.getValue());

                    if (TokenType.rightAssoc(token.getValue()) ? precedence < o2prec : precedence <= o2prec) {
                        output.push(operatorStack.pop());
                    }
                    else break;
                }

                operatorStack.push(token);
            }
            if (token.getType() == TokenType.LPAREN) {
                operatorStack.push(token);
            }
            if (token.getType() == TokenType.RPAREN) {
                while ( operatorStack.peek().getType() != TokenType.LPAREN ) {
                    output.push(operatorStack.pop());
                    if (operatorStack.empty()) throw new RuntimeException("Unmatched parens");
                }

                operatorStack.pop(); // Clear LParen
            }
        }

        while (!operatorStack.empty())
            output.push(operatorStack.pop());

        return output;
    }
}
