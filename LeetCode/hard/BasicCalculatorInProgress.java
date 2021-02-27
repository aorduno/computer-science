package LeetCode.hard;

import CTCI.LogUtils;

import java.util.Stack;

public class BasicCalculatorInProgress {
    public static void main(String[] args) {
        testCase(" -2 + 1 ");
        testCase(" 2-1 + 2 ");
        testCase("21 + 3");
        testCase(" 1 + 1 ");
        testCase("1+1");
        testCase("1");
        testCase("2-1+ 2 ");
        testCase("(1+(4+5+2)-3)+(6+8)");
    }

    private static void testCase(String operation) {
        LogUtils.logMessage("[[BasicCalculatorInProgress]] computing basic calculations for given operation: " + operation);

        int result = computeOperation(operation);
        LogUtils.logMessage("Result: " + result);
    }

//    public static int calculate(String s) {
//
//        Stack<Integer> stack = new Stack<Integer>();
//        int operand = 0;
//        int result = 0; // For the on-going result
//        int sign = 1;  // 1 means positive, -1 means negative
//
//        for (int i = 0; i < s.length(); i++) {
//
//            char ch = s.charAt(i);
//            if (Character.isDigit(ch)) {
//
//                // Forming operand, since it could be more than one digit
//                operand = 10 * operand + (int) (ch - '0');
//
//            } else if (ch == '+') {
//
//                // Evaluate the expression to the left,
//                // with result, sign, operand
//                result += sign * operand;
//
//                // Save the recently encountered '+' sign
//                sign = 1;
//
//                // Reset operand
//                operand = 0;
//
//            } else if (ch == '-') {
//
//                result += sign * operand;
//                sign = -1;
//                operand = 0;
//
//            } else if (ch == '(') {
//
//                // Push the result and sign on to the stack, for later
//                // We push the result first, then sign
//                stack.push(result);
//                stack.push(sign);
//
//                // Reset operand and result, as if new evaluation begins for the new sub-expression
//                sign = 1;
//                result = 0;
//
//            } else if (ch == ')') {
//
//                // Evaluate the expression to the left
//                // with result, sign and operand
//                result += sign * operand;
//
//                // ')' marks end of expression within a set of parenthesis
//                // Its result is multiplied with sign on top of stack
//                // as stack.pop() is the sign before the parenthesis
//                result *= stack.pop();
//
//                // Then add to the next operand on the top.
//                // as stack.pop() is the result calculated before this parenthesis
//                // (operand on stack) + (sign on stack * (result from parenthesis))
//                result += stack.pop();
//
//                // Reset the operand
//                operand = 0;
//            }
//        }
//        return result + (sign * operand);
//    }

    private static int computeOperation(String operation) {
        Stack<Integer> operators = new Stack<>();
        int number = 0;
        int exponent = 1;
        int result = 0;
        int sign = 1;
        for (int x = operation.length() - 1; x >= 0; x--) {
            char current = operation.charAt(x);
            if (current == ' ') {
                continue;
            }

            if (isNumber(current)) {
                number = (number * exponent) + current - '0';
                exponent *= 10;
            } else if (current == '+' || current == '-') {
                result += sign * number;

                sign = current == '+' ? 1 : -1;
                number = 0;
            } else if (current == '(') {
                operators.add(result);
                operators.add(sign);

                result = 0;
                sign = 1;
            } else {
                result += sign * number;
                result *= operators.pop();
                result += operators.pop();

                number = 0;
            }

        }

        return result + (number * sign);
    }

    private static int computeOperationReverse(String operation) {
        Stack<Object> chars = new Stack<>();
        int number = 0;
        int exponent = 1;
        for (int x = operation.length() - 1; x >= 0; x--) {
            char current = operation.charAt(x);
            if (current == ' ') {
                continue;
            }

            if (isNumber(current)) {
                number = ((current - '0') * exponent) + number;
                exponent *= 10;
            } else {
                if (number != 0) {
                    chars.push(number);
                    number = 0;
                    exponent = 1;
                }

                if (current == '(') {
                    int result = computeStack(chars);
                    chars.push(result);
                } else {
                    chars.push(current);
                }
            }
        }

        if (number != 0) {
            chars.push(number);
        }


        return computeStack(chars);
    }

    private static int computeStack(Stack<Object> chars) {
        if (chars.isEmpty()) {
            return 0;
        }

        int result = (int) chars.pop();
        while (!chars.isEmpty() && (char) chars.peek() != ')') {
            char operator = (char) chars.pop();
            if (operator == '+') {
                result += (int) chars.pop();
            } else {
                result -= (int) chars.pop();
            }
        }

        if (!chars.isEmpty()) {
            chars.pop(); // pop )
        }
        return result;
    }


    private static boolean isOperator(int index, String operation) {
        return operation.charAt(index) == '+' || operation.charAt(index) == '-';
    }

    private static boolean shouldSkip(int index, String operation) {
        char c = operation.charAt(index);
        return c == ' ' || c == '(' || c == ')';
    }

    private static boolean isNumber(char c) {
        return c != '(' && c != ')' && c != '-' && c != '+' && c != ' ';
    }
}
