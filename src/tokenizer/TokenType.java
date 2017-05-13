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
}
