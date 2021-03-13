package DataStructures.Graph;

import CTCI.TreesAndGraphs.GraphUtils;

public class GraphG {
    private GraphNodeG[] _nodes;
    private boolean _undirected;

    public GraphNodeG[] getNodes() {
        return _nodes;
    }

    public boolean isUndirected() {
        return _undirected;
    }

    // nodes = number of nodes, 0-index based
    public GraphG(int nodes, boolean isUndirected) {
        _nodes = new GraphNodeG[nodes];
        _undirected = isUndirected;
        for (int i = 0; i < nodes; i++) {
            _nodes[i] = new GraphNodeG<>(i);
        }
    }

    public static void main(String[] args) {
        GraphG graphG = new GraphG(8, false);
        graphG.connect(0, 1);
        graphG.connect(0, 2);
        graphG.connect(1, 5);
        graphG.connect(5, 6);
        graphG.connect(6, 7);
        graphG.connect(2, 3);
        graphG.connect(3, 4);

        GraphG graphG2 = new GraphG(8, true);
        graphG2.connect(0, 1);
        graphG2.connect(0, 2);
        graphG2.connect(1, 5);
        graphG2.connect(5, 6);
        graphG2.connect(6, 7);
        graphG2.connect(2, 3);
        graphG2.connect(3, 4);

        GraphUtils.print(graphG);
        GraphUtils.print(graphG2);

        GraphUtils.printDepthFirstSearch(graphG);
        GraphUtils.printBreadthFirstSearch(graphG);

    }

    public void connect(int from, int to) {
        _nodes[from].getAdjacencyList().add(_nodes[to]);
        if (_undirected) {
            _nodes[to].getAdjacencyList().add(_nodes[from]);
        }
    }
}
