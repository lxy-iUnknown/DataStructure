package com.lxy.datastructure.expression;

import com.lxy.datastructure.util.Contract;

public class Token {
    public static final Token ADD = symbolToken(TokenKind.ADD);
    public static final Token SUBTRACT = symbolToken(TokenKind.SUBTRACT);
    public static final Token MULTIPLY = symbolToken(TokenKind.MULTIPLY);
    public static final Token DIVIDE = symbolToken(TokenKind.DIVIDE);
    public static final Token LEFT_BRACKET = symbolToken(TokenKind.LEFT_BRACKET);
    public static final Token RIGHT_BRACKET = symbolToken(TokenKind.RIGHT_BRACKET);

    private final int value;
    private final TokenKind kind;

    private Token(int value, TokenKind kind) {
        this.value = value;
        this.kind = kind;
    }

    public Token(int value) {
        this(value, TokenKind.NUMBER);
    }

    private static Token symbolToken(TokenKind kind) {
        return new Token(0, kind);
    }

    public int getValue() {
        return value;
    }

    public TokenKind getKind() {
        return kind;
    }

    @Override
    public int hashCode() {
        return kind == TokenKind.NUMBER ? value : kind.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token token && kind == token.kind) {
            return kind != TokenKind.NUMBER || value == token.value;
        }
        return false;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Token{");
        switch (kind) {
            case TokenKind.NUMBER -> sb.append(value);
            case TokenKind.ADD -> sb.append("'+'");
            case TokenKind.SUBTRACT -> sb.append("'-'");
            case TokenKind.MULTIPLY -> sb.append("'*'");
            case TokenKind.DIVIDE -> sb.append("'/'");
            case TokenKind.LEFT_BRACKET -> sb.append("'('");
            case TokenKind.RIGHT_BRACKET -> sb.append("')'");
            default -> Contract.unreachable();
        }
        return sb.append('}').toString();
    }
}
