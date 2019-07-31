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

---------------------------------------------------------------------------------------------------------------

Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 

Example 1:
	Input: "abbaca"
	Output: "ca"
	Explanation: For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 
Note:
	1. 1 <= S.length <= 20000
	2. S consists only of English lowercase letters.
*/

class MySolution {
    public String removeDuplicates(String S) {
        if (S.length() == 1) return S;
        
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c)
                stack.pop();
            else
                stack.push(c);
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : stack)
            sb.append(c);
        
        return sb.toString();
    }
}

class Solution1 {
    public String removeDuplicates(String S) {
        char[] stack = S.toCharArray();

        int top = -1;
        for (char c : stack) {
            if (top > -1 && stack[top] == c) {
                top--;
                continue;
            } else {
                stack[++top] = c;
            }
        }
        return new String(stack, 0, top + 1);
    }
}
