package expr;

import token.TokenType;

public class BinaryExpr extends Expr {
    private Expr left;
    private Expr right;
    private TokenType op;

    public BinaryExpr(Expr left, Expr right, TokenType op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public double eval() {
        switch (op) {
        case PLUS:
            return left.eval() + right.eval();
        case MINUS:
            return left.eval() - right.eval();
        case STAR:
            return left.eval() * right.eval();
        case SLASH:
            return left.eval() / right.eval();
        case CARET:
            return Math.pow(left.eval(), right.eval());
        default:
            return Double.NaN;
        }
    }
}
