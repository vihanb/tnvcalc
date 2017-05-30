package expr;

public class NullExpr extends Expr {
    public NullExpr() {
    }

    public double eval() {
        return Double.NaN;
    }

    public String toString() {
        return "(null)";
    }
}
