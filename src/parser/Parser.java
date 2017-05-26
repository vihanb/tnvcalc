package parser;

import expr.BinaryExpr;
import expr.Expr;
import expr.FuncExpr;
import expr.NullExpr;
import expr.NumberExpr;
import expr.UnaryExpr;
import token.Token;
import token.TokenType;
import java.util.Queue;

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
        case NUMBER:
            return new NumberExpr(Double.parseDouble(token.getText()));
        case FUNCTION:
            return new FuncExpr(token.getText(), this.parse(100));
        case MINUS:
            return new UnaryExpr(this.parse(100), TokenType.MINUS);
        case LPAREN:
            Expr expr = this.parse(0);
            if (this.peek().getType() == TokenType.RPAREN) {
                return new NullExpr();
            }
            return expr;
        default:
            return new NullExpr();
        }
    }

    protected Expr led(Expr left, Token token) {
        switch (token.getType()) {
        case PLUS:
        case MINUS:
        case STAR:
        case SLASH:
            System.out.println("binary expr");
            return new BinaryExpr(left, this.parse(this.lbp(token)),
                token.getType());
        case CARET:
            return new BinaryExpr(left, this.parse(this.lbp(token) - 1),
                token.getType());
        default:
            return new NullExpr();
        }
    }

    protected int lbp(Token token) {
        switch (token.getType()) {
        case PLUS:
        case MINUS:
            return 10;
        case STAR:
        case SLASH:
            return 20;
        case CARET:
            return 30;
        default:
            return 0; // placeholder
        }
    }
}
