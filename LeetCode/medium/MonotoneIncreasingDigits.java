package LeetCode.medium;

import CTCI.LogUtils;

public class MonotoneIncreasingDigits {
    public static void main(String[] args) {
        testCase(10);
        testCase(1234);
        testCase(1243);
        testCase(8);
        testCase(1221);
        testCase(1232);
        testCase(1243);
        testCase(777616726);
    }

    private static void testCase(int number) {
        LogUtils.logMessage("[[MonotoneIncreasingDigits]] Finding out the largest number with monotonic increasing digits that is less or equal to " + number);

        int monotoneIncreasing = computeMonotoneBetter(number);
        LogUtils.logMessage("Monotone: " + monotoneIncreasing);
    }

    private static int computeMonotoneBetter(int number) {
        int[] num = convert(number);
        int index = 0;
        while (index < num.length - 1) {
            if (num[index] > num[index + 1]) {
                break;
            }
            index++;
        }

        fixForward(index, num);
        fixBackward(index, num);
        return convert(num);
    }

    private static void fixForward(int index, int[] num) {
        // done
        if (index == num.length - 1) {
            return;
        }

        // subtract from index and update the rest of num to 9...
        num[index] -= 1;
        int forwardIndex = index + 1;
        while (forwardIndex < num.length) {
            num[forwardIndex] = 9;
            forwardIndex++;
        }
    }

    private static void fixBackward(int index, int[] num) {
        // done
        if (index - 1 < 0 || num[index - 1] <= num[index]) {
            return;
        }

        int backwardIndex = index - 1;
        int previous = -1;
        while (backwardIndex >= 0) {
            if (previous != -1 && previous >= num[backwardIndex]) {
                break;
            }

            num[backwardIndex + 1] = 9;
            num[backwardIndex] -= 1;

            previous = num[backwardIndex];
            backwardIndex--;
        }
    }

    private static int convert(int[] number) {
        int converted = 0;
        for (int digit : number) {
            converted = (converted * 10) + digit;
        }

        return converted;
    }

    private static int[] convert(int number) {
        int length = getSizeOf(number);
        int[] converted = new int[length];
        int exponent = 10;
        int index = length - 1;
        while (number > 0) {
            int digit = (number % exponent) / (exponent / 10);
            converted[index] = digit;
            number -= (exponent / 10) * digit;
            exponent *= 10;
            index--;
        }
        return converted;
    }

    private static int getSizeOf(int number) {
        int exponent = 10;
        int length = 0;
        while (number > 0) {
            int digit = (number % exponent) / (exponent / 10);
            number -= (exponent / 10) * digit;
            exponent *= 10;
            length++;
        }

        return length;
    }

    private static int computeMonotoneBrute(int number) {
        while (number > 0) {
            if (isMonotoneIncreasing(number)) {
                return number;
            }

            number--;
        }
        return number;
    }

    private static boolean isMonotoneIncreasing(int number) {
        int exponent = 10;
        int previous = -1;
        while (number > 0) {
            int digit = (number % exponent) / (exponent / 10);
            if (previous != -1 && previous < digit) {
                return false;
            }

            number -= (exponent / 10) * digit;

            previous = digit;
            exponent *= 10;
        }

        return true;
    }
}
