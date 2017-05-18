package parser;

import java.util.Queue;
import expr.Expr;
import token.Token;

public class Parser {
    private Queue<Token> tokens;

    public Parser(Queue<Token> tokens) {
        this.tokens = tokens;
    }

    public Expr parse(int rbp) {
        Token t = next();
        Expr left = t.nud(this);
        while (t.lbp() > rbp) {
            t = next();
            left = t.led(this, left);
        }
        return left;
    }

    public Token next() {
        return tokens.remove();
    }

    public Token peek() {
        return tokens.element();
    }
}
