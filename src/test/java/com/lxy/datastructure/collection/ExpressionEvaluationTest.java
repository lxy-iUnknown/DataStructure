package com.lxy.datastructure.collection;

import com.lxy.datastructure.expression.ExpressionEvaluation;
import com.lxy.datastructure.expression.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressionEvaluationTest {

    // - * / 15 - 7 + 1 1 3 + 2 + 1 1
    private static final Token[] PREFIX = {
            Token.SUBTRACT, Token.MULTIPLY, Token.DIVIDE,
            new Token(15), Token.SUBTRACT, new Token(7),
            Token.ADD, new Token(1), new Token(1),
            new Token(3), Token.ADD, new Token(2),
            Token.ADD, new Token(1), new Token(1)
    };

    // ((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1))
    private static final Token[] INFIX = {
            Token.LEFT_BRACKET, Token.LEFT_BRACKET, new Token(15),
            Token.DIVIDE, Token.LEFT_BRACKET, new Token(7),
            Token.SUBTRACT, Token.LEFT_BRACKET, new Token(1),
            Token.ADD, new Token(1), Token.RIGHT_BRACKET,
            Token.RIGHT_BRACKET, Token.RIGHT_BRACKET, Token.MULTIPLY,
            new Token(3), Token.RIGHT_BRACKET, Token.SUBTRACT,
            Token.LEFT_BRACKET, new Token(2), Token.ADD,
            Token.LEFT_BRACKET, new Token(1), Token.ADD,
            new Token(1), Token.RIGHT_BRACKET, Token.RIGHT_BRACKET
    };

    // 15 7 1 1 + - / 3 * 2 1 1 + + -
    private static final Token[] POSTFIX = {
            new Token(15), new Token(7), new Token(1),
            new Token(1), Token.ADD, Token.SUBTRACT, Token.DIVIDE,
            new Token(3), Token.MULTIPLY, new Token(2),
            new Token(1), new Token(1), Token.ADD,
            Token.ADD, Token.SUBTRACT
    };

    private static final int EVALUATION_RESULT = 5;

    @Test
    public void testInfixToPreFix() {
        Assertions.assertArrayEquals(PREFIX, ExpressionEvaluation.infixToPrefix(INFIX));
    }

    @Test
    public void testInfixToPostFix() {
        Assertions.assertArrayEquals(POSTFIX, ExpressionEvaluation.infixToPostfix(INFIX));
    }

    @Test
    public void testEvaluatePrefix() {
        Assertions.assertEquals(EVALUATION_RESULT, ExpressionEvaluation.evaluatePrefix(PREFIX));
    }

    @Test
    public void testEvaluateInfix() {
        Assertions.assertEquals(EVALUATION_RESULT, ExpressionEvaluation.evaluateInfix(INFIX));
    }

    @Test
    public void testEvaluatePostfix() {
        Assertions.assertEquals(EVALUATION_RESULT, ExpressionEvaluation.evaluatePostfix(POSTFIX));
    }
}
