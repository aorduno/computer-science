package CTCI.ArraysAndStrings;

public class SnailSortMatrix {
    // generates a snail sort matrix with x and y dimensions
    // note that both, x and y need to be greater than 2
    // time complexity o(x * y)
    // space complexity o(1) -- arguable since we expect the matrix to be built so not necessarily extra space
    private static int[][] build(int x, int y) {
        if (x < 2 || y < 2) {
            return new int[0][0];
        }

        int[][] snailSortMatrix = new int[x][y];
        int maxNumber = x * y;
        int currentNumber = 1;
        int row = 0;
        int column = 0;

        // boundaries
        int layers = 0;
        int minColumn = -1;
        int minRow = -1;
        int maxRow = x;
        int maxColumn = y;

        // need a way to check if which mode we're on..
        // is left to right, top to bottom, right to left or bottom to top
        int directionMode = 0;
        while (currentNumber <= maxNumber) {
            snailSortMatrix[row][column] = currentNumber; // paint
            // update as needed
            switch (directionMode) {
                case 0:
                    // update to printMatrix
                    column++;
                    if (column == maxColumn) {
                        column--;
                        directionMode = 1;
                        row++;
                    }
                    break;
                case 1:
                    row++;
                    if (row == maxRow) {
                        row--;
                        directionMode = 2;
                        column--;
                    }
                    break;
                case 2:
                    column--;
                    if (column == minColumn) {
                        column++;
                        directionMode = 3;
                        row--;

                        // about to complete layer -- update boundaries
                        layers++;
                        maxRow = x - layers;
                        minRow = layers - 1;
                        minColumn = layers - 1;
                        maxColumn = y - layers;
                    }
                    break;
                case 3:
                    row--;
                    if (row == minRow) {
                        directionMode = 0;

                        // done -- set baseline for next round
                        row = layers;
                        column = layers;
                    }
                    break;
            }
            currentNumber++;
        }

        return snailSortMatrix;
    }

    public static void main(String[] args) {
        doTestCase(4, 4);
        doTestCase(3, 4);
        doTestCase(2, 3);
        doTestCase(5, 5);
        doTestCase(9, 8);
    }

    private static void doTestCase(int x, int y) {
        System.out.println("Generating matrix of " + x + ", " + y + "\n");
        int[][] matrix = build(x, y);
        ArrayUtils.printMatrix(matrix);
    }
}
