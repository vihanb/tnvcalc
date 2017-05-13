import tokenizer.*;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        if (System.console() == null) {
            while (in.hasNextLine()) {
                System.out.println(Main.evaluate(in.nextLine()));
            }
        } else {
            while (true) {
                System.out.print("tnv> ");
                System.out.println(Main.evaluate(in.nextLine()));
            }
        }
    }

    private static String evaluate(String input) {
        Tokenizer tok = new Tokenizer();
        Stack<Token> toks = tok.feed(input);
        // System.out.println(toks);
        Stack<Token> evalStack = new Parser().parse(toks);
        // System.out.println(evalStack.toString());
        Evaluator e = new Evaluator(evalStack);
        return e.withBigDecimal();
    }
}
