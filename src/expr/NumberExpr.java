package expr;

public class NumberExpr extends Expr {
    private double value;

    public NumberExpr(double value) {
        this.value = value;
    }

    public double eval() {
        return value;
    }

    public String toString() {
        return "(number " + this.value + ")";
    }
}
