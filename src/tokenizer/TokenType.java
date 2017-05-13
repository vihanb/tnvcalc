package tokenizer;

import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("^[\\d]+"),
    OPERATOR("^[-+/*^]"),
    LPAREN("^\\("),
    RPAREN("^\\)");

    private String regex;
    TokenType(String regex) { this.regex = regex; }
    public Pattern getRegex() { return Pattern.compile(regex); }
    
    public static int getPrecedence(String operator) {
        switch (operator) {
            case "^": return 900;

            case "*":
            case "/": return 800;

            case "+":
            case "-": return 700;

            default: return 1000;
        }
    }

    public static boolean rightAssoc(String operator) {
        switch (operator) {
            case "^": return true;
            default: return false;
        }
    }
}
