package LeetCode;

public class ArrayProduct {

    // time: o(n^2)
    // space o(1)
    static int[] getProductArrayBrute(int[] original) {
        int oLength = original.length;
        int[] productArray = new int[oLength];
        for (int x = 0; x < oLength; x++) {
            Integer product = null;
            for (int y = 0; y < oLength; y++) {
                if (x != y) {
                    product = product == null ? original[y] : product * original[y];
                }
            }

            productArray[x] = product;
        }

        return productArray;
    }

    // time: o(n)
    // space: o(n)
    static int[] getProductArrayBetter(int[] original) {
        int originaLength = original.length;
        int[] traverseProduct = new int[originaLength];
        int[] reverseProduct = new int[originaLength];

        int product = original[0];
        traverseProduct[0] = original[0];
        for (int x = 1; x < originaLength; x++) {
            product *= original[x];
            traverseProduct[x] = product;
        }

        int lastPosition = originaLength - 1;
        product = original[lastPosition];
        reverseProduct[lastPosition] = original[lastPosition];
        for (int x = lastPosition - 1; x >= 0; x--) {
            product *= original[x];
            reverseProduct[x] = product;
        }

        int[] productArray = new int[originaLength];
        productArray[0] = reverseProduct[1];
        for (int x = 1; x < originaLength; x++) {
            int indexProduct = traverseProduct[x - 1];
            if (x + 1 < originaLength) {
                indexProduct *= reverseProduct[x + 1];
            }

            productArray[x] = indexProduct;
        }

        return productArray;
    }

    static void printArray(int[] array) {
        System.out.println("Array elements:");
        for (int x : array) {
            System.out.print(" " + x);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] e1 = new int[]{1, 2, 3, 4, 5};
        int[] e2 = new int[]{3, 2, 1};

        /** brute force **/
        System.out.println("BRUTE FORCE");
        System.out.println("Print original");
        printArray(e1);
        System.out.println("Print product");
        printArray(getProductArrayBrute(e1));

        System.out.println("Print original");
        printArray(e2);
        System.out.println("Print product");
        printArray(getProductArrayBrute(e2));

        /** optimal**/
        System.out.println("BETTER");
        System.out.println("Print original");
        printArray(e1);
        System.out.println("Print product");
        printArray(getProductArrayBetter(e1));

        System.out.println("Print original");
        printArray(e2);
        System.out.println("Print product");
        printArray(getProductArrayBetter(e2));
    }
}
