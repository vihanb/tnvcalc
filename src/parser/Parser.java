package parser;

import expr.Expr;
import token.Token;

public class Parser {
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
        return null; // placeholder
    }

    public Token peek() {
        return null; // placeholder
    }
}
