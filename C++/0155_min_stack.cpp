/*
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
	1. push(x) -- 将元素 x 推入栈中。
	2. pop() -- 删除栈顶的元素。
	3. top() -- 获取栈顶元素。
	4. getMin() -- 检索栈中的最小元素。

示例:
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> 返回 -3.
	minStack.pop();
	minStack.top();      --> 返回 0.
	minStack.getMin();   --> 返回 -2.
*/

class MyMinStack {
public:
    /** initialize your data structure here. */
    MinStack() {
    }
    
    void push(int x) {
        if (x <= min)
        {
            sta.push(min);
            min = x;
        }
        sta.push(x);
    }
    
    void pop() {
        int popped = sta.top();
        sta.pop();
        if (popped == min)
        {
            min = sta.top();
            sta.pop();
        }
    }
    
    int top() {
        return sta.top();
    }
    
    int getMin() {
        return min;
    }
private:
    stack<int> sta;
    int min = INT_MAX;
};
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
