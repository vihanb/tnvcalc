import tokenizer.*;
import java.util.Stack;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
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
        long start = System.nanoTime();

//         Tokenizer tok = new Tokenizer();
//         Stack<Token> toks = tok.feed(input);
//         if (toks == null) return null;
//         Stack<Token> evalStack = new Parser().parse(toks);
//         Evaluator e = new Evaluator(evalStack);
// 
//         long end = System.nanoTime();
//         System.out.println("Operation took: " + ((double)(end - start) / 1000000) + "ms");

//         return e.withBigDecimal();
        return null;
    }
}
