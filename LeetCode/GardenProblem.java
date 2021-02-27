package LeetCode;

class Place {
    private int _x;
    private int _y;
    private int _flowers;

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setX(int _x) {
        this._x = _x;
    }

    public void setY(int _y) {
        this._y = _y;
    }

    public int getFlowers() {
        return _flowers;
    }

    public void setFlowers(int _flowers) {
        this._flowers = _flowers;
    }
}

class CellData {
    private int _left;
    private int _up;
    private int _right;
    private int _down;

    public int getLeft() {
        return _left;
    }

    public void setLeft(int _left) {
        this._left = _left;
    }

    public int getUp() {
        return _up;
    }

    public void setUp(int _up) {
        this._up = _up;
    }

    public int getRight() {
        return _right;
    }

    public void setRight(int _right) {
        this._right = _right;
    }

    public int getDown() {
        return _down;
    }

    public void setDown(int _down) {
        this._down = _down;
    }
}

public class GardenProblem {

    private static final String COLUMN = "+";
    private static final String FLOWER = "*";
    private static final String BLANK_SPACE = " ";

    private Place findPlace(String[][] garden) {
        return doFindPlace(garden);
    }

    private Place doFindPlace(String[][] garden) {
        int n = garden.length;
        int m = garden[0].length;

        CellData[][] cellsData = new CellData[n][m];
        initCellsData(cellsData);
        Place place = new Place();
        int maxFlowers = 0;

        // pass for left and up
        maxFlowers = populateLeftAndUp(garden, n, m, cellsData, place, maxFlowers);

        // now pass for right and down, we can get totals now...
        maxFlowers = populateTotals(garden, n, m, cellsData, place, maxFlowers);

        place.setFlowers(maxFlowers);
        return place;
    }

    // populates right, down and totals
    private int populateTotals(String[][] garden, int n, int m, CellData[][] cellsData, Place place, int maxFlowers) {
        for (int x = n - 1; x >= 0; x--) {
            for (int y = m - 1; y >= 0; y--) {
                String s = garden[x][y];
                CellData currentCellData = cellsData[x][y];
                switch (s) {
                    case COLUMN:
                        currentCellData.setRight(0);
                        currentCellData.setDown(0);
                        break;
                    case FLOWER:
                    case BLANK_SPACE:
                        int totalRight = 0;
                        int neighborRightY = y + 1;
                        if (y < m - 1) {
                            String neighborRight = garden[x][neighborRightY];
                            totalRight += neighborRight.equals("*") ? 1 : 0;
                            totalRight += cellsData[x][neighborRightY].getRight();
                        }

                        int totalDown = 0;
                        int neighborDownX = x + 1;
                        if (x < n - 1) {
                            String neighborDown = garden[neighborDownX][y];
                            totalDown += neighborDown.equals("*") ? 1 : 0;
                            totalDown += cellsData[neighborDownX][y].getDown();
                        }

                        // store right and down
                        currentCellData.setRight(totalRight);
                        currentCellData.setDown(totalDown);

                        // at this point we have everything we need...
                        int total = totalRight + totalDown + currentCellData.getUp() + currentCellData.getLeft();
                        if (total > maxFlowers) {
                            maxFlowers = total;
                            place.setX(x);
                            place.setY(y);
                        }
                        break;
                    default:
                        // ignore
                }
            }
        }
        return maxFlowers;
    }

    private int populateLeftAndUp(String[][] garden, int n, int m, CellData[][] cellsData, Place place, int maxFlowers) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                String s = garden[x][y];
                switch (s) {
                    case COLUMN:
                        cellsData[x][y].setLeft(0);
                        cellsData[x][y].setUp(0);
                        break;
                    case FLOWER:
                    case BLANK_SPACE:
                        // check neighbor above
                        int totalLeft = 0;
                        if (y > 0) {
                            totalLeft += garden[x][y - 1].equals("*") ? 1 : 0;
                            totalLeft += cellsData[x][y - 1].getLeft();
                        }

                        int totalUp = 0;
                        if (x > 0) {
                            totalUp += garden[x - 1][y].equals("*") ? 1 : 0;
                            totalUp += cellsData[x - 1][y].getUp();
                        }

                        cellsData[x][y].setLeft(totalLeft);
                        cellsData[x][y].setUp(totalUp);
                        if (totalLeft + totalUp > maxFlowers) {
                            maxFlowers = totalLeft + totalUp;
                            place.setX(x);
                            place.setY(y);
                        }
                        break;
                    default:
                        // ignore
                }
            }
        }
        return maxFlowers;
    }

    private void initCellsData(CellData[][] cellsData) {
        int n = cellsData.length;
        int m = cellsData[0].length;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                CellData cellData = new CellData();
                cellData.setLeft(0);
                cellData.setUp(0);
                cellData.setRight(0);
                cellData.setDown(0);
                cellsData[x][y] = cellData;

            }
        }
    }

    static void printGarden(String[][] garden) {
        int n = garden.length;
        int m = garden[0].length;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                System.out.print(garden[x][y] + " ");
            }
            System.out.println();
        }

    }

    static void findAndPrint(String[][] garden) {
        GardenProblem gp = new GardenProblem();
        printGarden(garden);
        Place place = gp.findPlace(garden);
        System.out.println("The optimal place to sit to look at some flowers in this garden is given by the coordinates: (" + place.getX() + ", " + place.getY() + ")");
        System.out.println("With a total of " + place.getFlowers() + " flowers.");

    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
    }

    private static void testCase7() {
        String[][] garden = new String[][]{
                {" ", " ", " ", " ", " "},
                {" ", "+", " ", "+", " "},
                {" ", " ", "+", " ", " "},
                {" ", "+", " ", " ", " "},
                {" ", " ", " ", "+", "*"}
        };

        findAndPrint(garden);
    }

    private static void testCase6() {
        String[][] garden = new String[][]{
                {" ", " ", " ", " ", " "},
                {" ", "+", " ", "+", " "},
                {" ", " ", "+", " ", " "},
                {" ", "+", " ", " ", " "},
                {" ", " ", " ", "+", " "}
        };

        findAndPrint(garden);
    }

    private static void testCase5() {
        String[][] garden = new String[][]{
                {" ", " ", "*", "*", "*"},
                {"*", "+", "*", "+", "*"},
                {"+", "*", "+", " ", "*"},
                {" ", "+", " ", " ", "+"},
                {"*", " ", " ", "*", "*"}
        };

        findAndPrint(garden);
    }

    private static void testCase4() {
        String[][] garden = new String[][]{
                {"*", "*", " ", "*", "*"},
                {"*", "+", "*", "+", "*"},
                {"+", "*", "*", "*", "*"},
                {"*", "+", "*", "*", "+"},
                {"*", "*", "*", "*", "+"}
        };

        findAndPrint(garden);
    }

    private static void testCase3() {
        String[][] garden = new String[][]{
                {"*", "*", "*", "*", "*"},
                {"*", "+", "*", "+", "*"},
                {"+", "*", "*", "*", "*"},
                {"*", "+", "*", "*", "+"},
                {"*", "*", "*", "*", "+"}
        };

        findAndPrint(garden);
    }

    private static void testCase2() {
        String[][] garden = new String[][]{
                {"*", "*", "*", "*", "*"},
                {"*", "+", "*", "+", "*"},
                {"+", "*", "*", "*", "*"},
                {"*", "+", "+", "*", "+"},
                {"*", "*", "*", "*", "+"}
        };

        findAndPrint(garden);
    }

    private static void testCase1() {
        String[][] garden = new String[][]{
                {"*", "*", "+", "*", "*"},
                {"*", "+", "*", "+", "*"},
                {"+", "*", "*", "*", "*"},
                {"*", "+", "+", "*", "+"},
                {"*", "*", "*", "*", "+"}
        };

        findAndPrint(garden);
    }

    // this does three o(nxm) passes
    private Place findPlaceInitial(String[][] garden) {
        int n = garden.length;
        int m = garden[0].length;
        CellData[][] cellsData = new CellData[n][m];
        initCellsData(cellsData);
        Place place = new Place();
        int maxFlowers = 0;
        // pass for left and up
        maxFlowers = populateLeftAndUp(garden, n, m, cellsData, place, maxFlowers);

        // now pass for right and down, we can get totals now...
        maxFlowers = populateRightAndDown(garden, n, m, cellsData, place, maxFlowers);

        // at this point we have everything...
        for (int x = n - 1; x >= 0; x--) {
            for (int y = m - 1; y >= 0; y--) {
                CellData cellData = cellsData[x][y];
                int total = cellData.getLeft() + cellData.getUp() + cellData.getRight() + cellData.getDown();
                // check if is higher than what we found so far...
                if (total > maxFlowers) {
                    maxFlowers = total;
                    place.setX(x);
                    place.setY(y);
                }
            }
        }

        place.setFlowers(maxFlowers);
        return place;
    }

    private int populateRightAndDown(String[][] garden, int n, int m, CellData[][] cellsData, Place place, int maxFlowers) {
        for (int x = n - 1; x >= 0; x--) {
            for (int y = m - 1; y >= 0; y--) {
                String s = garden[x][y];
                switch (s) {
                    case COLUMN:
                        cellsData[x][y].setRight(0);
                        cellsData[x][y].setDown(0);
                        break;
                    case FLOWER:
                    case BLANK_SPACE:
                        // check neighbor above
                        int totalRight = 0;
                        int neighborRightY = y + 1;
                        if (y < m - 1) {
                            String neighborRight = garden[x][neighborRightY];
                            totalRight += neighborRight.equals("*") ? 1 : 0;
                            totalRight += cellsData[x][neighborRightY].getRight();
                        }

                        int totalDown = 0;
                        int neighborDownX = x + 1;
                        if (x < n - 1) {
                            String neighborDown = garden[neighborDownX][y];
                            totalDown += neighborDown.equals("*") ? 1 : 0;
                            totalDown += cellsData[neighborDownX][y].getDown();
                        }

                        cellsData[x][y].setRight(totalRight);
                        cellsData[x][y].setDown(totalDown);
                        if (totalRight + totalDown > maxFlowers) {
                            maxFlowers = totalRight + totalDown;
                            place.setX(x);
                            place.setY(y);
                        }
                        break;
                    default:
                        // ignore
                }
            }
        }
        return maxFlowers;
    }
}
