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
        Token t = this.next();
        Expr left = t.nud(this);
        while (t.lbp() > rbp) {
            t = this.next();
            left = t.led(this, left);
        }
        return left;
    }

    public Token next() {
        return this.tokens.remove();
    }

    public Token peek() {
        return this.tokens.element();
    }
}
