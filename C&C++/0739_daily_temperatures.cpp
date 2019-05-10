#include <iostream>
#include <vector>
#include <stack>

using std::vector;
using std::stack;
using std::pair;
using std::make_pair;

class Solution1 {
public:
	vector<int> dailyTemperatures(vector<int>& temperatures) 
	{
		stack<pair<int, int>>st;
		vector<int> nums(temperatures.size(), 0);
		for (int i = 0; i < temperatures.size(); i++) 
		{
			while (!st.empty() && st.top().first < temperatures[i]) 
			{
				nums[st.top().second] = (i - st.top().second);
				st.pop();
			}
			st.push(make_pair(temperatures[i], i));
		}
		return nums;
	}
};

class MySolution {
public:
	vector<int> dailyTemperatures(vector<int>& T) {
		vector<int> output;
		int size = T.size();
		for (int i = 0; i < size - 1; i++) {
			int j = i + 1;
			while (j < size) {
				if (T[j] > T[i]) {
					output.push_back(j - i);
					break;
				}
				j++;
			}
			if (j == size) output.push_back(0);
		}
		output.push_back(0);
		
		return output;
	}
};
