package com.lxy.datastructure.collection.stack;

import com.lxy.datastructure.collection.util.Contract;

import java.util.Arrays;
import java.util.Stack;

/**
 * 表达式求值相关算法
 */
public class ExpressionEvaluation {

    private static int invalidTokenKind(int tokenKind) {
        Contract.fail("Invalid token kind " + tokenKind);
        return 0;
    }

    private static int priority(int tokenKind) {
        return switch (tokenKind) {
            case TokenKind.ADD, TokenKind.SUBTRACT -> 1;
            case TokenKind.MULTIPLY, TokenKind.DIVIDE -> 2;
            default -> invalidTokenKind(tokenKind);
        };
    }

    private static void evaluatePostfixStep(Stack<Integer> numberStack, int tokenKind) {
        Contract.require(numberStack.size() >= 2,
                "Stack size is not sufficient");
        // 如果是运算符则弹出栈顶的两个操作数进行运算，栈顶为右操作数，次栈顶为左操作数
        // 例如：a b op对应的中缀表达式为a op b。由于遍历顺序为从左往右，即
        // push(a) -> push(b),所以b为栈顶，a为次栈顶
        int right = numberStack.pop();
        int left = numberStack.pop();
        numberStack.push(switch (tokenKind) {
            case TokenKind.ADD -> left + right;
            case TokenKind.SUBTRACT -> left - right;
            case TokenKind.MULTIPLY -> left * right;
            case TokenKind.DIVIDE -> left / right;
            default -> invalidTokenKind(tokenKind);
        });
    }

    private static int getEvaluatedResult(Stack<Integer> stack) {
        Contract.require(stack.size() == 1, "Stack size not equal to one");
        return stack.peek();
    }

    /**
     * 中缀表达式转前缀表达式<br>
     * 算法：从<b>右到左</b>遍历中缀表达式，对每一个token进行以下操作：<br>
     * 1. 如果是操作数则直接加入前缀表达式<br>
     * 2. 如果是<b>右</b>括号则入栈<br>
     * 3. 如果是<b>左</b>括号则不断弹出栈顶运算符并加入前缀表达式，
     * 直到遇到右括号，然后弹出右括号（但不加入前缀表达式）<br>
     * 4. 如果是运算符，则将栈中优先级<b>大于等于</b>当前运算符的运算符弹出并加入前缀表达式，
     * 然后该运算符入栈</br>
     * 当表达式扫描完毕后将栈中剩余运算符以此弹出并加入前缀表达式
     * @param infix 中缀表达式
     * @return 前缀表达式
     */
    public static Token[] infixToPrefix(Token[] infix) {
        int length = infix.length;
        Stack<Token> stack = new Stack<>();
        Token[] prefix = new Token[length];
        int index = length - 1;
        for (int i = length - 1; i >= 0; i--) {
            Token token = infix[i];
            int kind = token.getKind();
            switch (kind) {
                case TokenKind.NUMBER -> prefix[index--] = token;
                case TokenKind.ADD, TokenKind.SUBTRACT,
                        TokenKind.MULTIPLY, TokenKind.DIVIDE-> {
                    int priority = priority(kind);
                    while (!stack.isEmpty()) {
                        Token top = stack.peek();
                        if (top.getKind() == TokenKind.RIGHT_BRACKET ||
                                priority(top.getKind()) > priority) {
                            break;
                        }
                        prefix[index--] = top;
                        stack.pop();
                    }
                    stack.push(token);
                }
                case TokenKind.RIGHT_BRACKET -> stack.push(token);
                case TokenKind.LEFT_BRACKET -> {
                    while (!stack.isEmpty()) {
                        Token top = stack.peek();
                        if (top.getKind() == TokenKind.RIGHT_BRACKET) {
                            stack.pop();
                            break;
                        }
                        prefix[index--] = top;
                        stack.pop();
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            prefix[index--] = stack.pop();
        }
        return Arrays.copyOfRange(prefix, index + 1, length);
    }

    /**
     * 中缀表达式转后缀表达式<br>
     * 算法：从<b>左到右</b>遍历中缀表达式，对每一个token进行以下操作：<br>
     * 1. 如果是操作数则直接加入后缀表达式<br>
     * 2. 如果是<b>左</b>括号则入栈<br>
     * 3. 如果是<b>右</b>括号则不断弹出栈顶运算符并加入后缀表达式，
     * 直到遇到左括号，然后弹出左括号（但不加入后缀表达式）<br>
     * 4. 如果是运算符，则将栈中优先级<b>大于等于</b>当前运算符的运算符弹出并加入后缀表达式，
     * 然后该运算符入栈</br>
     * 当表达式扫描完毕后将栈中剩余运算符以此弹出并加入后缀表达式
     * @param infix 中缀表达式
     * @return 后缀表达式
     */
    @SuppressWarnings("ForLoopReplaceableByForEach")
    public static Token[] infixToPostfix(Token[] infix) {
        int length = infix.length;
        Stack<Token> stack = new Stack<>();
        Token[] postfix = new Token[length];
        int postfixLength = 0;
        for (int i = 0; i < length; i++) {
            Token token = infix[i];
            int kind = token.getKind();
            switch (kind) {
                case TokenKind.NUMBER -> postfix[postfixLength++] = token;
                case TokenKind.ADD, TokenKind.SUBTRACT,
                        TokenKind.MULTIPLY, TokenKind.DIVIDE-> {
                    int priority = priority(kind);
                    while (!stack.isEmpty()) {
                        Token top = stack.peek();
                        if (top.getKind() == TokenKind.LEFT_BRACKET ||
                                priority(top.getKind()) < priority) {
                            break;
                        }
                        postfix[postfixLength++] = top;
                        stack.pop();
                    }
                    stack.push(token);
                }
                case TokenKind.LEFT_BRACKET -> stack.push(token);
                case TokenKind.RIGHT_BRACKET -> {
                    while (!stack.isEmpty()) {
                        Token top = stack.peek();
                        if (top.getKind() == TokenKind.LEFT_BRACKET) {
                            stack.pop();
                            break;
                        }
                        postfix[postfixLength++] = top;
                        stack.pop();
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix[postfixLength++] = stack.pop();
        }
        return Arrays.copyOfRange(postfix, 0, postfixLength);
    }

    /**
     * 前缀表达式求值
     * @param prefix 前缀表达式
     * @return 前缀表达式的值
     */
    public static int evaluatePrefix(Token[] prefix) {
        // 操作数栈
        Stack<Integer> stack = new Stack<>();
        for (int i = prefix.length - 1; i >= 0; i--) {
            Token token = prefix[i];
            int kind = token.getKind();
            if (kind == TokenKind.NUMBER) {
                // 如果是操作数则入栈
                stack.push(token.getValue());
            } else {

                Contract.require(stack.size() >= 2,
                        "Stack size is not sufficient");
                // 如果是运算符则弹出栈顶的两个操作数进行运算，栈顶为左操作数，次栈顶为右操作数
                // 例如：a b op对应的中缀表达式为a op b。由于遍历顺序为从右往左，即
                // push(b) -> push(a), 所以a为栈顶，b为次栈顶
                int left = stack.pop();
                int right = stack.pop();
                stack.push(switch (kind) {
                    case TokenKind.ADD -> left + right;
                    case TokenKind.SUBTRACT -> left - right;
                    case TokenKind.MULTIPLY -> left * right;
                    case TokenKind.DIVIDE -> left / right;
                    default -> invalidTokenKind(kind);
                });
            }
        }
        return getEvaluatedResult(stack);
    }

    /**
     * 中缀表达式求值<br>
     * 算法：设置一个操作数栈和一个运算符栈，从<b>左往右</b>遍历中缀表达式，按照中缀转后缀的算法处理每一个token
     * （参见{@link ExpressionEvaluation#infixToPostfix(Token[])}）<br>。
     * 每当一个运算符从运算符栈中出栈时就将操作数栈中的两个操作数弹出进行相应的运算。
     * 如果中缀表达式合法，那么操作数栈最终只会有一个元素，这个元素就是中缀表达式的值
     * @param infix 中缀表达式
     * @return 中缀表达式的值
     */
    public static int evaluateInfix(Token[] infix) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Token> operatorStack = new Stack<>();
        for (Token token : infix) {
            int kind = token.getKind();
            switch (kind) {
                case TokenKind.NUMBER -> numberStack.push(token.getValue());
                case TokenKind.ADD, TokenKind.SUBTRACT,
                        TokenKind.MULTIPLY, TokenKind.DIVIDE-> {
                    int priority = priority(kind);
                    while (!operatorStack.isEmpty()) {
                        Token top = operatorStack.peek();
                        if (top.getKind() == TokenKind.LEFT_BRACKET ||
                                priority(top.getKind()) < priority) {
                            break;
                        }
                        evaluatePostfixStep(numberStack, top.getKind());
                        operatorStack.pop();
                    }
                    operatorStack.push(token);
                }
                case TokenKind.LEFT_BRACKET -> operatorStack.push(token);
                case TokenKind.RIGHT_BRACKET -> {
                    while (!operatorStack.isEmpty()) {
                        Token top = operatorStack.peek();
                        if (top.getKind() == TokenKind.LEFT_BRACKET) {
                            operatorStack.pop();
                            break;
                        }
                        evaluatePostfixStep(numberStack, top.getKind());
                        operatorStack.pop();
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            evaluatePostfixStep(numberStack, operatorStack.pop().getKind());
        }
        return getEvaluatedResult(numberStack);
    }

    /**
     * 后缀表达式求值
     * @param postfix 后缀表达式
     * @return 后缀表达式的值
     */
    public static int evaluatePostfix(Token[] postfix) {
        // 操作数栈
        Stack<Integer> stack = new Stack<>();
        for (Token token : postfix) {
            int kind = token.getKind();
            if (kind == TokenKind.NUMBER) {
                // 如果是操作数则入栈
                stack.push(token.getValue());
            } else {
                evaluatePostfixStep(stack, kind);
            }
        }
        return getEvaluatedResult(stack);
    }
}
