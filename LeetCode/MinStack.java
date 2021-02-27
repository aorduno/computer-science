package LeetCode;

import DataStructures.Stack;

class MinStack {
    private Stack _internalStack; // acts as normal stack
    private Stack _minStack; // used to keep the min always at top


    /**
     * initialize your data structure here.
     */
    public MinStack() {
        _internalStack = new Stack();
        _minStack = new Stack();
    }

    // to make sure minStack is always at top we need to...
    // 1) check what our current min is (if any)
    // 2) compare our current min with what's being pushed into stack
    // 2.1) if, it's less than min or stacks are empty -- push to both
    // 2.2) if, it's not -- push min to min stack AND current to internalStack
    public void push(int x) {
        if (_minStack.isEmpty()) {
            pushToBoth(x);
            return;
        }

        int min = _minStack.peek();
        if (x < min) {
            pushToBoth(x);
        } else {
            _minStack.add(_minStack.peek());
            _internalStack.add(x);
        }
    }

    private void pushToBoth(int x) {
        _minStack.add(x);
        _internalStack.add(x);
    }

    public void pop() {
        _internalStack.pop();
        _minStack.pop();
    }

    public int top() {
        return _internalStack.peek();
    }

    public int getMin() {
        return _minStack.peek();
    }

    static void print(int x) {
        System.out.println("value: " + x);
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        print(minStack.getMin());//   --> Returns -3.
        minStack.pop();
        print(minStack.top());     // --> Returns 0.
        print(minStack.getMin());  // --> Returns -2.

        MinStack minStack2 = new MinStack();
        minStack2.push(-2);
        minStack2.push(0);
        minStack2.push(-1);
        print(minStack2.getMin());//   --> Returns -2.
        minStack2.pop();
        print(minStack2.top());     // --> Returns 0.
        print(minStack2.getMin());  // --> Returns -2.
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */