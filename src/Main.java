import tokenizer.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Tokenizer tok = new Tokenizer();
        Stack<Token> toks = tok.feed("1+1*2");
        System.out.println(toks);
        System.out.println(new Parser().parse(toks).toString());
    }
}
