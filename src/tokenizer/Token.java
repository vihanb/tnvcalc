package tokenizer;

public class Token {
    private TokenType token;
    private String value;

    public Token(TokenType token, String value) {
        this.token = token;
        this.value = value;
    }

    public TokenType getToken() { return this.token; }
    public String getValue() { return this.value; }

    public String toString() { return this.value; }
}
