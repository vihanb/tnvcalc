package parser;

import java.util.Queue;
import expr.Expr;
import token.Token;
import token.TokenType;

public class Parser {
    private Queue<Token> tokens;

    public Parser(Queue<Token> tokens) {
        this.tokens = tokens;
    }

    public Expr parse(int rbp) {
        Token token = this.next();
        Expr left = this.nud(token);
        while (this.lbp(token) > rbp) {
            token = this.next();
            left = this.led(left, token);
        }
        return left;
    }

    public Token next() {
        return this.tokens.remove();
    }

    public Token peek() {
        return this.tokens.element();
    }

    protected Expr nud(Token token) {
        switch (token.getType()) {
        default:
            return null; // placeholder
        }
    }

    protected Expr led(Expr left, Token token) {
        switch (token.getType()) {
        default:
            return null; // placeholder
        }
    }

    protected int lbp(Token token) {
        switch (token.getType()) {
        default:
            return 0; // placeholder
        }
    }
}
