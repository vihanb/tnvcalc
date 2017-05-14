package parser;

import java.util.List;
import expr.Expr;
import token.Token;

public class Parser {
    private List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        pos = 0;
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
        Token t = peek();
        ++pos;
        return t;
    }

    public Token peek() {
        return tokens.get(pos);
    }
}
