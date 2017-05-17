package token;

import expr.Expr;
import parser.Parser;

public abstract class Token {
    private int pos;

    public Token(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public Expr nud(Parser parser) {
        return null; // placeholder
    }

    public Expr led(Parser parser, Expr left) {
        return null; // placeholder
    }

    public int lbp() {
        return 0; // placeholder
    }
}
