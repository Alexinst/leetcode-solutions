/*
 * https://leetcode-cn.com/problems/validate-stack-sequences/

给定 pushed 和 popped 两个序列，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。

示例 1：
	输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
	输出：true
	解释：我们可以按以下顺序执行：
	push(1), push(2), push(3), push(4), pop() -> 4,
	push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

示例 2：
	输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
	输出：false
	解释：1 不能在 2 之前弹出。

提示：
	1. 0 <= pushed.length == popped.length <= 1000
	2. 0 <= pushed[i], popped[i] < 1000
	3. pushed 是 popped 的排列。

-------------------------------------------------------------------------------------------------------------------------------

Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

Example 1:
	Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
	Output: true
	Explanation: We might do the following sequence:
	push(1), push(2), push(3), push(4), pop() -> 4,
	push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

Example 2:
	Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
	Output: false
	Explanation: 1 cannot be popped before 2.

Note:
	1. 0 <= pushed.length == popped.length <= 1000
	2. 0 <= pushed[i], popped[i] < 1000
	3. pushed is a permutation of popped.
	4. pushed and popped have distinct values.
*/

class Solution1 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int size = 0;
        
        for (int i = 0, j = 0; i < pushed.length; i++) {
            pushed[size++] = pushed[i];
            while (size > 0 && pushed[size - 1] == popped[j]) {
                size--;
                j++;
            }
        }
        
        return size == 0;
    }
}

class Solution2 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack();

        int j = 0;
        for (int x: pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }
}
