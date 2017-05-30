package expr;

public class FuncExpr extends Expr {
    private String func;
    private Expr arg;

    public FuncExpr(String func, Expr arg) {
        this.func = func;
        this.arg = arg;
    }

    public double eval() {
        switch (func.toLowerCase()) {
        case "acos":
            return Math.acos(arg.eval());
        case "asin":
            return Math.asin(arg.eval());
        case "atan":
            return Math.atan(arg.eval());
        case "cos":
            return Math.cos(arg.eval());
        case "cosh":
            return Math.cosh(arg.eval());
        case "ln":
            return Math.log(arg.eval());
        case "log":
            return Math.log10(arg.eval());
        case "sin":
            return Math.sin(arg.eval());
        case "sinh":
            return Math.sinh(arg.eval());
        case "sqrt":
            return Math.sqrt(arg.eval());
        case "tan":
            return Math.tan(arg.eval());
        case "tanh":
            return Math.tanh(arg.eval());
        default:
            return Double.NaN;
        }
    }

    public String toString() {
        return "(" + this.func + " " + this.arg.toString() + ")";
    }
}
