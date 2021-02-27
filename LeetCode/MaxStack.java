package LeetCode;

import DataStructures.Stack;

class MaxStack {
    private Stack _internalStack; // acts as normal stack
    private Stack _maxStack; // used to keep the max always at top


    /**
     * initialize your data structure here.
     */
    public MaxStack() {
        _internalStack = new Stack();
        _maxStack = new Stack();
    }

    // to make sure maxStack is always at top we need to...
    // 1) check what our current max is (if any)
    // 2) compare our current max with what's being pushed into stack
    // 2.1) if, it's less than max or stacks are empty -- push to both
    // 2.2) if, it's not -- push max to max stack AND current to internalStack
    public void push(int x) {
        if (_maxStack.isEmpty()) {
            pushToBoth(x);
            return;
        }

        int max = _maxStack.peek();
        if (x > max) {
            pushToBoth(x);
        } else {
            _maxStack.add(_maxStack.peek());
            _internalStack.add(x);
        }
    }

    private void pushToBoth(int x) {
        _maxStack.add(x);
        _internalStack.add(x);
    }

    public void pop() {
        _internalStack.pop();
        _maxStack.pop();
    }

    public int top() {
        return _internalStack.peek();
    }

    public int getMax() {
        return _maxStack.peek();
    }

    static void print(int x) {
        System.out.println("value: " + x);
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(2);
        maxStack.push(0);
        maxStack.push(3);
        print(maxStack.getMax());//   --> Returns 3
        maxStack.pop();
        print(maxStack.top());     // --> Returns 0.
        print(maxStack.getMax());  // --> Returns 2.
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