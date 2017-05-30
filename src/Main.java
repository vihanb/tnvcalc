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
    private static Tokenizer tokenizer;
    private static Parser parser;

    public static void main(String[] args) {
        Main.tokenizer = new Tokenizer();
        Main.parser = new Parser();
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
        Queue<Token> tokens = Main.tokenizer.feed(input);
        if (tokens == null) {
            return "Format error";
        }
        Main.parser.setTokens(tokens);
        Expr ast = Main.parser.parse(0);
        if (ast == null) {
            return "Syntax error";
        }
        double value = ast.eval();
        if (Double.isNaN(value)) {
            return "Math error";
        }
        Main.parser.setPreviousAnswer(value);
        return Double.toString(value);
    }
}
