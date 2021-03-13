package DataStructures.Graph;

public class UndirectedGraphAMatrix {
    private final int _vertices;
    private int[][] _adjancecyMatrix;

    public UndirectedGraphAMatrix(int vertices) {
        _vertices = vertices;
        _adjancecyMatrix = new int[vertices][vertices];

        for (int x = 0; x < vertices; x++) {
            for (int y = 0; y < vertices; y++) {
                _adjancecyMatrix[x][y] = 0;
            }
        }
    }

    public int[][] getAdjancecyMatrix() {
        return _adjancecyMatrix;
    }

    public void addEdge(int x, int y) {
        _adjancecyMatrix[x][y] = 1;

        // undirected means both directions
        _adjancecyMatrix[y][x] = 1;
    }

    void removeEdge(int x, int y) {
        _adjancecyMatrix[x][y] = 0;
    }

    //addVertex for this is expensive..
    // we would need to...
    // 1) recreate the matrix (or copy)
    // 2) augment by 1
    // 3) update matrix values for new vertex with 0


    public void print() {
        for (int x = 0; x < _vertices; x++) {
            System.out.print("Adjancecy list for " + x);
            for (int y = 0; y < _vertices; y++) {
                int i = _adjancecyMatrix[x][y];
                if (i == 0) {
                    continue;
                }
                System.out.print(" -> " + y);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        UndirectedGraphAMatrix undirectedGraphAMatrix = new UndirectedGraphAMatrix(5);

        undirectedGraphAMatrix.addEdge(0, 1);
        undirectedGraphAMatrix.addEdge(0, 2);
        undirectedGraphAMatrix.addEdge(1, 3);
        undirectedGraphAMatrix.addEdge(2, 4);

        int[][] adjancecyMatrix = undirectedGraphAMatrix.getAdjancecyMatrix();
        undirectedGraphAMatrix.print();
    }
}
