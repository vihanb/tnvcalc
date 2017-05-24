package token;

public class Token {
    private TokenType type;
    private String text;
    private int pos;

    public Token(TokenType type, String text, int pos) {
        this.type = type;
        this.text = text;
        this.pos = pos;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    public int getPos() {
        return this.pos;
    }
}
