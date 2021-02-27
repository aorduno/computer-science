package LeetCode;

//In base -2, integers are represented by sequences of bits in the following way. Bits are ordered from the least to the most significant. Sequence B of N bits represents the number: sum{B[i]*(-2)^i for i= 0..N}. The empty sequence represents 0.
//        Note that such a representation is suitable for both positive and negative integers.
//        Write a function:
//class Solution{ public int[] solution(int[] A);}
//that, given a zero-indexed array A of M bits, containing a sequence representing some integer X, returns the shortest sequence of bits representing -X.
//        The sequence should be returned as:
//        - a structure Results (in C), or
//        - a vector of integers (in C++), or
//        - a record Results (in Pascal), or
//        - an array of integers (in any other programming language).
//        For example,
//        given A= [1,0,0,1,1](X=9), the function should return [1,1,0,1](-X=-9)
//        given A= [1,0,0,1,1,1](X=-23), the function should return [1,1,0,1,0,1,1](-X=23)
//        Assume that:
//        - M is an integer within the range [0..100,000];
//        - each element of the array A is an integer that can have one of the following values: 0,1.
//        Complexity:
//        - Expected worst-case time complexity is O(M);
//        - Expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
//        Elements of the input array can be modified.
public class NegaBinaries {
    private byte[] getMinusX(byte[] x) {
        int xLength = x.length;
        if (xLength == 0) {
            return new byte[0];
        }

        // -2x
        byte[] minusTwoX = getMinusTwoX(x, xLength + 1);
        // performs x - 2x = -x
        byte[] minusX = getMinusX(x, xLength, minusTwoX);
        // strips out unnecessary 0
        return shortify(minusX);
    }

    private byte[] getMinusX(byte[] x, int xLength, byte[] minusTwoX) {
        boolean carry = false;
        byte[] minusX = new byte[minusTwoX.length];
        for (int pos = 0; pos < minusTwoX.length; pos++) {
            byte xBitValue = pos <= xLength - 1 ? x[pos] : 0; // x is shorter than minusTwo so... default to 0
            byte minusTwoBitValue = minusTwoX[pos];
            boolean xBit = xBitValue == 1;
            boolean minusTwoBit = minusTwoBitValue == 1;
            // in this type of sequence... carry means substract so...
            // substract from current
            if (carry && xBit) {
                xBit = false;
                carry = false;
            }

            if (carry && minusTwoBit) {
                minusTwoBit = false;
                carry = false;
            }

            if (xBit & minusTwoBit) {
                minusX[pos] = 0;
                carry = true;
            } else if (xBit ^ minusTwoBit) {
                minusX[pos] = 1;
                carry = false;
            } else {
                minusX[pos] = 0;
            }

        }
        return minusX;
    }

    // get -2 * x
    private byte[] getMinusTwoX(byte[] x, int minusTwoLength) {
        byte[] minusTwoX = new byte[minusTwoLength];
        minusTwoX[0] = 0;
        for (int y = 1; y < minusTwoLength; y++) {
            int from = y - 1;
            minusTwoX[y] = x[from];
        }

        return minusTwoX;
    }

    // removes unnecessary 0s os it's the shortest sequence
    private byte[] shortify(byte[] minusX) {
        int position = 0;
        // find the most significant
        for (int x = minusX.length - 1; x >= 0; x--) {
            position = x;
            if (minusX[x] == 1) {
                break;
            }
        }

        // ignore the rest
        byte[] shortest = new byte[position + 1];
        for (int x = 0; x <= position; x++) {
            shortest[x] = minusX[x];
        }

        return shortest;
    }

    public static void main(String[] args) {
        doTest(new byte[]{1});// 1
        doTest(new byte[]{0, 1, 1});// 2
        doTest(new byte[]{1, 1, 1});// 3
        doTest(new byte[]{0, 0, 1});// 4
        doTest(new byte[]{1, 0, 1});// 5
        doTest(new byte[]{0, 1, 0, 1, 1});// 6
        doTest(new byte[]{1, 1, 0, 1, 1});// 7
        doTest(new byte[]{0, 0, 0, 1, 1});// 8
        doTest(new byte[]{1, 0, 0, 1, 1});// 9
        doTest(new byte[]{0, 1, 1, 1, 1});// 10

        doTest(new byte[]{1, 0, 0, 1, 1, 1});// 23

        doTest(new byte[]{1, 0, 0, 1, 1, 1, 0, 0, 0, 1});// 535


    }

    private static void doTest(byte[] sequence) {
        System.out.print("X ");
        print(sequence);
        NegaBinaries nb = new NegaBinaries();
        byte[] minusX = nb.getMinusX(sequence);
        System.out.print("-X ");
        print(minusX);
    }

    private static void print(byte[] sequence) {
        System.out.print("Sequence is... ");
        int total = 0;
        for (int x = 0; x < sequence.length; x++) {
            byte current = sequence[x];
            System.out.print(current);
            if (x < sequence.length - 1) {
                System.out.print(", ");
            }
            total += current * Math.pow(-2, x);
        }

        System.out.println();
        System.out.println("Which is decimal " + total);
        System.out.println();
    }
}
