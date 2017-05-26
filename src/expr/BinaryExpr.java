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

    public String toString() {
        String operation;
        switch (op) {
        case PLUS:
            operation = "add";
            break;
        case MINUS:
            operation = "subtract";
            break;
        case STAR:
            operation = "multiply";
            break;
        case SLASH:
            operation = "divide";
            break;
        case CARET:
            operation = "power";
            break;
        default:
            operation = "error";
        }
        return "(" + operation + " " + left.toString() + ", " +
            right.toString() + ")";
    }
}
