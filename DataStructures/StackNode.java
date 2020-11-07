package DataStructures;

public class StackNode<T> {
    public T _data;
    public StackNode<T> _next;

    public StackNode(T data) {
        _data = data;
    }

    public T getData() {
        return _data;
    }
}
