package token;

import java.util.regex.Pattern;

public enum TokenType {
    PLUS("^\\+"),
    MINUS("^\\-"),
    STAR("^\\*"),
    SLASH("^\\/"),
    CARET("^\\^"),
    LPAREN("^\\("),
    RPAREN("^\\)"),
    NUMBER("^-?(\\d*\\.\\d+|\\d+|pi|PI|e|E)"),
    FUNCTION("\\p{Alpha}+"),
    EOF("$");

    private Pattern regex;

    private TokenType(String regex) {
        this.regex = Pattern.compile(regex);
    }

    public Pattern getRegex() {
        return this.regex;
    }
}
