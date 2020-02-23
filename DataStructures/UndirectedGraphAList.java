package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphAList {
    private int _vertices;
    List<List<Integer>> _adjancecyList = new ArrayList<>();

    public UndirectedGraphAList(int vertices) {
        _vertices = vertices;

        for (int x = 0; x < vertices; x++) {
            _adjancecyList.add(new ArrayList<>());
        }
    }

    // o(1)
    public void addEdge(int x, int y) {
        _adjancecyList.get(x).add(y);
    }

    // o(n)
    void removeEdge(int x, int y) {
        List<Integer> listEntry = _adjancecyList.get(x);
        int i = listEntry.indexOf(y);
        if (i != -1) {
            listEntry.remove(y);
        }
    }

    public void addVertex() {
        _adjancecyList.add(new ArrayList<>());
        _vertices = _vertices + 1;
    }

    public List<Integer> getAdjancencyList(int x) {
        return _adjancecyList.get(x);
    }

    void print() {
        for (int x = 0; x < _vertices; x++) {
            System.out.print("Printing adjancy list for " + x);
            int size = _adjancecyList.get(x).size();
            if (size == 0) {
                System.out.print(" no adjancent vertices found!");
            }
            for (int y = 0; y < size; y++) {
                // no need to validate here.. cause if this are not adjacent entry just won't exist...
                System.out.print(" -> " + _adjancecyList.get(x).get(y));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        UndirectedGraphAList undirectedGraphAList = new UndirectedGraphAList(5);

        undirectedGraphAList.addEdge(0, 1);
        undirectedGraphAList.addEdge(0, 2);
        undirectedGraphAList.addEdge(1, 3);
        undirectedGraphAList.addEdge(2, 4);
        undirectedGraphAList.print();

        // new edges
        undirectedGraphAList.addVertex();
        undirectedGraphAList.addEdge(4, 5);

        undirectedGraphAList.print();
    }
}
