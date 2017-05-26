package token;

import java.util.regex.Pattern;

public enum TokenType {
    NUMBER("^-?(\\d*\\.\\d+|\\d+)"),
    OPERATOR("^[-+/*^]"),
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
