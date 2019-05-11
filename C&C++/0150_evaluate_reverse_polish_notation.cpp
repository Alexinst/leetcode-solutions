/*
根据逆波兰表示法，求表达式的值。
有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：
	整数除法只保留整数部分。
	给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

示例 1：
	输入: ["2", "1", "+", "3", "*"]
	输出: 9
	解释: ((2 + 1) * 3) = 9

示例 2：
	输入: ["4", "13", "5", "/", "+"]
	输出: 6
	解释: (4 + (13 / 5)) = 6

示例 3：
	输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
	输出: 22
	解释: 
		  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
		= ((10 * (6 / (12 * -11))) + 17) + 5
		= ((10 * (6 / -132)) + 17) + 5
		= ((10 * 0) + 17) + 5
		= (0 + 17) + 5
		= 17 + 5
		= 22

*/

#include <iostream>
#include <stack>
#include <vector>
#include <string>

using std::string;
using std::vector;
using std::stack;

class MySolution {
public:
	int evalRPN(vector<string>& tokens) {
		stack<int> nums;
		for (int i = 0; i < tokens.size(); i++) {
			if (!isOperator(tokens[i]))
				nums.push(stoi(tokens[i]));
			else {
				int rightNum = nums.top();
				nums.pop();
				int leftNum = nums.top();
				nums.pop();
				int result = handle(leftNum, rightNum, tokens[i].c_str());
				nums.push(result);
			}
		}
		return nums.top();
	}

	bool isOperator(string & s) {
		return s == "*" || s == "/" || s == "+" || s == "-";
	}

	int handle(int num1, int num2, const char * c) {
		switch (*c) {
		case '+': return num1 + num2;
		case '-': return num1 - num2;
		case '*': return num1 * num2;
		case '/': return num1 / num2;
		}
	}
};
