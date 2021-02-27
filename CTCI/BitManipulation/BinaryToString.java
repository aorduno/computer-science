package CTCI.BitManipulation;

public class BinaryToString {
    public static void main(String[] args) {
        testCase(0.101);
        testCase(0.72);
        testCase(0.01);
    }

    private static void testCase(double num) {
        System.out.println("[[BinaryToString]]Getting binary representation of " + num);
        String binaryString = getBinaryString(num);
        System.out.println("Result: " + binaryString);
    }

    private static String getBinaryString(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR: Invalid input";
        }

        StringBuilder binaryStringBuilder = new StringBuilder(".");
        while (num > 0) {
            // max _length allowed is 32.
            if (binaryStringBuilder.length() >= 32) {
                return "ERROR: max _length allowed is 32";
            }

            double shifted = num * 2;
            if (shifted >= 1) {
                binaryStringBuilder.append("1");
                num = shifted - 1;
            } else {
                binaryStringBuilder.append("0");
                num = shifted;
            }
        }
        return binaryStringBuilder.toString();
    }
}
