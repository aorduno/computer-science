package DataStructures.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeG<T> {
    private List<GraphNodeG> _adjacencyList = new ArrayList<>();
    public T _data;

    GraphNodeG(T data) {
        _data = data;
    }

    public T getData() {
        return _data;
    }

    public List<GraphNodeG> getAdjacencyList() {
        return _adjacencyList;
    }
}
