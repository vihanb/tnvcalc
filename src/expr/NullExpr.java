package expr;

public class NullExpr extends Expr {
    public NullExpr() {
    }

    public double eval() {
        return Double.NaN;
    }
}
