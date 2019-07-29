/*
 *

使用栈实现队列的下列操作：
	push(x) -- 将一个元素放入队列的尾部。
	pop() -- 从队列首部移除元素。
	peek() -- 返回队列首部的元素。
	empty() -- 返回队列是否为空。

示例:
	MyQueue queue = new MyQueue();

	queue.push(1);
	queue.push(2);  
	queue.peek();  // 返回 1
	queue.pop();   // 返回 1
	queue.empty(); // 返回 false
	
说明:
	1. 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
	2. 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
	3. 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。

-----------------------------------------------------------------------------------------------------------------

Implement the following operations of a queue using stacks.
	push(x) -- Push element x to the back of queue.
	pop() -- Removes the element from in front of queue.
	peek() -- Get the front element.
	empty() -- Return whether the queue is empty.

Example:
	MyQueue queue = new MyQueue();

	queue.push(1);
	queue.push(2);  
	queue.peek();  // returns 1
	queue.pop();   // returns 1
	queue.empty(); // returns false

Notes:
	1. You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
	2. Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
	3. You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

class MyQueue {
    
    private Stack<Integer> stack;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!empty()) {
            Stack<Integer> tmp = new Stack<>();
            while (!stack.isEmpty()) 
                tmp.push(stack.pop());

            int head = tmp.pop();
            while (!tmp.isEmpty()) 
                stack.push(tmp.pop());

            return head;
        }
        
        return -1;
    }
    
    /** Get the front element. */
    public int peek() {
        if (!empty()) {
            Stack<Integer> tmp = new Stack<>();
            while (!stack.isEmpty()) 
                tmp.push(stack.pop());

            int head = tmp.peek();
            while (!tmp.isEmpty()) 
                stack.push(tmp.pop());

            return head;            
        }
        return -1;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;
    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>(); 
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!s1.empty()) s2.push(s1.pop());
        s1.push(x);
        while (!s2.empty()) s1.push(s2.pop());
        return;
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return s1.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty(); 
    }
}
