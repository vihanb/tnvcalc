package token;

import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("^-?(\\d*\\.\\d+|\\d+)"),
    FUNCTION("\\p{Alpha}+"),
    PLUS("^\\+"),
    MINUS("^\\-"),
    STAR("^\\*"),
    SLASH("^\\/"),
    CARET("^\\^"),
    LPAREN("^\\("),
    RPAREN("^\\)");

    private Pattern regex;

    private TokenType(String regex) {
        this.regex = Pattern.compile(regex);
    }

    public Pattern getRegex() {
        return this.regex;
    }
}
