package easy;

import java.util.Stack;

public class ValidParenthesis {
    public static void main(String[] args) {
        test("]");
        test("()");
        test("[{(())}]");
        test("[{(())]}");
        test("(()}");
        test("");
        test(null);
    }

    private static void test(String expression) {
        System.out.println("[[ValidParenthesis]] Checking if given expression: " + expression + " isValid!");
        System.out.println("isValid: " + isExpressionValidBetter(expression));
    }

    // assumes expression is only formed by valid open and close tags... no validation is needed..
    // time complex o(n) = n being expression's length
    // space complex o(n) = n being expression's length
    private static boolean isExpressionValidBetter(String expression) {
        if (expression == null || expression.length() == 0) { // assuming empties are valid
            return true;
        }

        Stack<Character> openTags = new Stack<>();
        for (int x = 0; x < expression.length(); x++) {
            char current = expression.charAt(x);
            if (isOpenTag(current)) {
                openTags.push(current);
            } else if (isClosingTag(current)) {
                if (!isValidOrder(current, openTags)) {
                    return false;
                }

                openTags.pop();
            }
        }
        return openTags.isEmpty();
    }

    // time o(n) = n being expression's length
    // space o(n) = additional space for stack
    private static boolean isExpressionValid(String expression) {
        if (expression == null || expression.length() == 0) { // assuming empties are valid
            return true;
        }

        int curlies = 0;
        int squares = 0;
        int parens = 0;
        Stack<Character> openTags = new Stack<>();
        for (int x = 0; x < expression.length(); x++) {
            char current = expression.charAt(x);
            if (isOpenTag(current)) {
                openTags.push(current);
                switch (current) {
                    case '{':
                        curlies++;
                        break;
                    case '[':
                        squares++;
                        break;
                    case '(':
                        parens++;
                        break;
                }
            } else if (isClosingTag(current)) {
                if (!isValidOrder(current, openTags)) {
                    return false;
                }

                openTags.pop();
                switch (current) {
                    case '}':
                        curlies--;
                        break;
                    case ']':
                        squares--;
                        break;
                    case ')':
                        parens--;
                        break;
                }
            }
        }
        return curlies == 0 && parens == 0 && squares == 0;
    }

    private static boolean isValidOrder(char current, Stack<Character> openTags) {
        if (openTags.isEmpty()) {
            return false;
        }
        switch (current) {
            case '}':
                return openTags.peek() == '{';
            case ']':
                return openTags.peek() == '[';
            case ')':
                return openTags.peek() == '(';
        }

        return false;
    }

    private static boolean isClosingTag(char current) {
        return current == '}' || current == ')' || current == ']';
    }

    private static boolean isOpenTag(char current) {
        return current == '{' || current == '(' || current == '[';
    }
}

