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
    private double previousAnswer;

    public Parser() {
        this.tokens = null;
        this.previousAnswer = Double.NaN;
    }

    public void setTokens(Queue<Token> tokens) {
        this.tokens = tokens;
    }

    public double getPreviousAnswer() {
        return previousAnswer;
    }

    public void setPreviousAnswer(double previousAnswer) {
        this.previousAnswer = previousAnswer;
    }

    public Expr parse(int rbp) {
        Token token = this.next();
        Expr left = this.nud(token);
        while (this.lbp(this.peek()) > rbp) {
            token = this.next();
            left = this.led(left, token);
        }
        return left;
    }

    public Token next() {
        Token token = this.tokens.poll();
        if (token == null) {
            return new Token(TokenType.EOF, "");
        }
        return token;
    }

    public Token peek() {
        Token token = this.tokens.peek();
        if (token == null) {
            return new Token(TokenType.EOF, "");
        }
        return token;
    }

    protected Expr nud(Token token) {
        switch (token.getType()) {
        case NUMBER:
            double value;
            switch (token.getText().toLowerCase()) {
            case "pi":
                value = Math.PI;
                break;
            case "e":
                value = Math.E;
                break;
            case "ans":
                value = this.previousAnswer;
                break;
            default:
                value = Double.parseDouble(token.getText());
            }
            return new NumberExpr(value);
        case FUNCTION:
            return new FuncExpr(token.getText(), this.parse(100));
        case MINUS:
            return new UnaryExpr(this.parse(100), TokenType.MINUS);
        case LPAREN:
            Expr expr = this.parse(0);
            if (this.peek().getType() != TokenType.RPAREN) {
                System.out.println("error: unmatched lparen");
                return new NullExpr();
            }
            this.next();
            return expr;
        default:
            System.out.println("error: " + token + " is not a unary operator");
            return new NullExpr();
        }
    }

    protected Expr led(Expr left, Token token) {
        switch (token.getType()) {
        case PLUS:
        case MINUS:
        case STAR:
        case SLASH:
            return new BinaryExpr(left, this.parse(this.lbp(token)),
                token.getType());
        case CARET:
            return new BinaryExpr(left, this.parse(this.lbp(token) - 1),
                token.getType());
        default:
            System.out.println("error: " + token + " is not a binary operator");
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
            return 0;
        }
    }
}
