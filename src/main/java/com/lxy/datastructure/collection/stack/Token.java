package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.util.Contract;

public class Token {
    public static final Token ADD = symbolToken(TokenKind.ADD);
    public static final Token SUBTRACT = symbolToken(TokenKind.SUBTRACT);
    public static final Token MULTIPLY = symbolToken(TokenKind.MULTIPLY);
    public static final Token DIVIDE = symbolToken(TokenKind.DIVIDE);
    public static final Token LEFT_BRACKET = symbolToken(TokenKind.LEFT_BRACKET);
    public static final Token RIGHT_BRACKET = symbolToken(TokenKind.RIGHT_BRACKET);

    private final int value;
    private final int kind;

    private Token(int value, int kind) {
        this.value = value;
        this.kind = kind;
    }

    private static Token symbolToken(int kind) {
        return new Token(0, kind);
    }

    public Token(int value) {
        this(value, TokenKind.NUMBER);
    }

    public int getValue() {
        return value;
    }

    public int getKind() {
        return kind;
    }

    @Override
    public int hashCode() {
        return kind == TokenKind.NUMBER ? value : kind;
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
        StringBuilder sb = new StringBuilder();
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
