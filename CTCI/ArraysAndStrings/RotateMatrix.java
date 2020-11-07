package CTCI.ArraysAndStrings;

public class RotateMatrix {
    // this rotates an n x n matrix by 90 degrees
    // time complexity o(n^2)
    // space complexity o(n^2)
    private static int[][] rotate(int[][] matrix) {
        int length = matrix.length;
        int[][] rotated = new int[length][length];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                int offsetX = getOffsetX(x, length);
                rotated[y][offsetX] = matrix[x][y];
            }
        }

        return rotated;
    }

    // this rotates in place, using multiple circular layers approach...
    // rotate from left -> top, bottom -> left, right -> bottom, top -> right
    // time complexity o(n^2)
    // space complexity o(1)
    private static void rotateInPlace(int[][] matrix) {
        int length = matrix.length;
        for (int layer = 0; layer < length / 2; layer++) {
            int firstLayerPosition = layer; // firstLayerPosition position in matrix to rotate from
            int lastLayerPosition = length - 1 - layer; // lastLayerPosition position in matrix to rotate from
            for (int x = firstLayerPosition; x < lastLayerPosition; x++) {
                int offset = x - firstLayerPosition;

                int top = matrix[firstLayerPosition][x]; // temp

                // left -> top
                matrix[firstLayerPosition][x] = matrix[lastLayerPosition - offset][firstLayerPosition];

                // bottom -> left
                matrix[lastLayerPosition - offset][firstLayerPosition] = matrix[lastLayerPosition][lastLayerPosition - offset];

                // right -> bottom
                matrix[lastLayerPosition][lastLayerPosition - offset] = matrix[x][lastLayerPosition];

                // top -> right
                matrix[x][lastLayerPosition] = top;
            }
        }
    }

    private static int getOffsetX(int position, int length) {
        return length - 1 - position;
    }

    public static void main(String[] args) {
        evaluate(10);
        evaluate(5);
        evaluate(4);
        evaluate(3);
        evaluate(2);
        evaluate(1);
    }

    private static void evaluate(int n) {
        int[][] matrix = createMatrix(n);
        System.out.println("Original Matrix:");
        ArrayUtils.printMatrix(matrix);
//        System.out.println("After rotating 90 degrees: ");
//        int[][] rotated = rotate(matrix);
//        ArrayUtils.printMatrix(rotated);

        System.out.println("After rotating 90 degrees IN PLACE: ");
        rotateInPlace(matrix);
        ArrayUtils.printMatrix(matrix);
    }

    private static int[][] createMatrix(int n) {
        int[][] matrix = new int[n][n];
        int number = 1;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                matrix[x][y] = number;
                number++;
            }
        }

        return matrix;
    }
}
