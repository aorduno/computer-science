package LeetCode.easy;

import CTCI.LogUtils;

public class RobotReturnOrigin {
    public static void main(String[] args) {
        testCase("UD");
        testCase("LL");
        testCase("RRDD");
        testCase("LDRRLRUULR");
        testCase("LDRRLRUULRDL");
    }

    private static void testCase(String moves) {
        LogUtils.logMessage("[[RobotReturnOrigin]] Determining if robot returns to origin for given moves: " + moves);
        boolean returnsToOrigin = computeReturnToOrigin(moves);
        LogUtils.logMessage("Result: " + returnsToOrigin);
    }

    private static boolean computeReturnToOrigin(String moves) {
        int vertical = 0;
        int horizontal = 0;
        for (int x = 0; x < moves.length(); x++) {
            switch (moves.charAt(x)) {
                case 'D':
                    vertical--;
                    break;
                case 'U':
                    vertical++;
                    break;
                case 'L':
                    horizontal--;
                    break;
                case 'R':
                    horizontal++;
                    break;
            }
        }

        return vertical == 0 && horizontal == 0;
    }
}
