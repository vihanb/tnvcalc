package token;

import expr.Expr;
import parser.Parser;

public abstract class Token {
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
