package expr;

import token.TokenType;

public class UnaryExpr extends Expr {
    private Expr expr;
    private TokenType op;

    public UnaryExpr(Expr expr, TokenType op) {
        this.expr = expr;
        this.op = op;
    }

    public double eval() {
        switch (op) {
        case MINUS:
            return -expr.eval();
        default:
            return Double.NaN;
        }
    }
}
