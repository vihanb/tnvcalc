import tokenizer.*;

public class Main {
    public static void main(String[] args) {
        Tokenizer tok = new Tokenizer();
        System.out.println(tok.feed("1+1").toString());
    }
}
