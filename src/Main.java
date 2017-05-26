import expr.Expr;
import parser.Parser;
import token.Token;
import tokenizer.Tokenizer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Queue;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
        List<String> argv = Arrays.asList(args);
        boolean shouldTime = argv.contains("-time");

        Scanner in = new Scanner(System.in);
        Console c;
        if ((c = System.console()) == null) {
            while (in.hasNextLine()) {
                System.out.println(Main.evaluate(in.nextLine(), shouldTime));
            }
        } else {
            while (true) {
                System.out.print("tnv> ");
                System.out.println(Main.evaluate(c.readLine(), shouldTime));
            }
        }
    }

    private static String evaluate(String input, boolean shouldTime) {
        Tokenizer tokenizer = new Tokenizer();
        Queue<Token> tokens = tokenizer.feed(input);
        if (tokens == null) {
            return "Error";
        }
        Expr ast = new Parser(tokens).parse(0);
        if (ast == null) {
            return "Error";
        }
        double value = ast.eval();
        if (Double.isNaN(value)) {
            return "Error";
        }
        return Double.toString(value);
    }
}
