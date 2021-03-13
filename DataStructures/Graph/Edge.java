package DataStructures.Graph;

public class Edge implements Comparable<Edge> {
    private int _from;
    private int _to;
    private int _weight;

    @Override
    public int compareTo(Edge edge) {
        return _weight - edge._weight;
    }

    Edge(int from, int to, int weight) {
        _from = from;
        _to = to;
        _weight = weight;
    }

    public int getFrom() {
        return _from;
    }

    public void set_from(int _from) {
        this._from = _from;
    }

    public int getTo() {
        return _to;
    }

    public void set_to(int _to) {
        this._to = _to;
    }

    public int getWeight() {
        return _weight;
    }
}
