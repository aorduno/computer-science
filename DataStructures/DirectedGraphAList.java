package DataStructures;

import java.util.*;
import java.util.LinkedList;

public class DirectedGraphAList {
    private int _vertices;
    private List<List<Integer>> _adjancecyList = new ArrayList<>();

    public int getVertices() {
        return _vertices;
    }

    public DirectedGraphAList(int vertices) {
        _vertices = vertices;

//        _adjancecyList = new List[vertices];
        for (int x = 0; x < vertices; x++) {
            _adjancecyList.add(new ArrayList<>());
//            _adjancecyList[x] = new ArrayList<Integer>();
        }
    }

    // o(1)
    public void addEdge(int x, int y) {
        _adjancecyList.get(x).add(y);
//        /_adjancecyList[x].add(y);
    }

    // o(n)
    void removeEdge(int x, int y) {
//        int i = _adjancecyList[x].indexOf(y);
        List<Integer> listEntry = _adjancecyList.get(x);
        int i = listEntry.indexOf(y);
        if (i != -1) {
//            _adjancecyList[x].remove(i);
            listEntry.remove(y);
        }
    }

    public void addVertex() {
//        _adjancecyList[_vertices] = new ArrayList();
        _adjancecyList.add(new ArrayList<>());
        _vertices = _vertices + 1;
    }

    public boolean hasPathBFS(int source, int destination) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> nextToVisit = new java.util.LinkedList<>();

        nextToVisit.add(source);
        while (!nextToVisit.isEmpty()) {
            Integer visitedNode = ((LinkedList<Integer>) nextToVisit).remove();
            if (visited.contains(visitedNode)) {
                continue;
            }

            visited.add(visitedNode);
            if (visitedNode == destination) {
                return true;
            }

            nextToVisit.addAll(getAdjancencyList(visitedNode));
        }

        return false;
    }

    public boolean hasPathDFS(int source, int destination) {
        Set<Integer> visited = new HashSet<>();
        return hasPathDFS(source, destination, visited);
    }

    private boolean hasPathDFS(int source, int destination, Set<Integer> visited) {
        if (visited.contains(source)) {
            return false;
        }

        visited.add(source);
        if (source == destination) {
            return true;
        }

        for (Integer adjacent : getAdjancencyList(source)) {
            if (hasPathDFS(adjacent, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public List<Integer> getAdjancencyList(int x) {
        return _adjancecyList.get(x);
    }

    boolean isCyclic() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();

        // check every tree
//        for (int x = 0; x < _vertices; x++) {
//            if (isCyclic(x, visited, recStack)) {
//                return true;
//            }
//        }
        if (isCyclic(0, visited, recStack)) {
            return true;
        }

        return false;
    }

    private boolean isCyclic(int vertex, Set<Integer> visited, Set<Integer> recStack) {
        if (recStack.contains(vertex)) {
            return true;
        }

        if (visited.contains(vertex)) {
            return false;
        }

        visited.add(vertex);
        recStack.add(vertex);
        List<Integer> adjancencyList = getAdjancencyList(vertex);
        for (Integer anAdjancencyList : adjancencyList) {
            int adjacent = anAdjancencyList;
            if (isCyclic(adjacent, visited, recStack)) {
                return true;
            }
        }

        recStack.remove(vertex);
        return false;
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
        DirectedGraphAList undirectedGraphAList = new DirectedGraphAList(5);

        undirectedGraphAList.addEdge(0, 1);
        undirectedGraphAList.addEdge(1, 2);
        undirectedGraphAList.addEdge(2, 0);
        undirectedGraphAList.addEdge(2, 3);
        undirectedGraphAList.addEdge(3, 3);

        if (undirectedGraphAList.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");

        undirectedGraphAList.print();

        DirectedGraphAList undirectedGraphAList2 = new DirectedGraphAList(5);

        undirectedGraphAList2.addEdge(0, 1);
        undirectedGraphAList2.addEdge(0, 2);
        undirectedGraphAList2.addEdge(1, 2);
        undirectedGraphAList2.addEdge(2, 3);
        undirectedGraphAList2.addEdge(3, 4);

        if (undirectedGraphAList2.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");

        undirectedGraphAList2.print();

        DirectedGraphAList undirectedGraphAList3 = new DirectedGraphAList(3);

        undirectedGraphAList3.addEdge(0, 1);
        undirectedGraphAList3.addEdge(1, 2);
        undirectedGraphAList3.addEdge(0, 2);

        if (undirectedGraphAList3.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");

        undirectedGraphAList3.print();
    }
}
