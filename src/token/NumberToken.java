package token;

public class NumberToken extends Token {
    private Number value;

    public NumberToken(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }
}
