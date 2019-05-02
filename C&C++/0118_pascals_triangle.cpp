/*
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:
	输入: 5
	输出:
		[
			 [1],
			[1,1],
		   [1,2,1],
		  [1,3,3,1],
		 [1,4,6,4,1]
		]
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
	vector<vector<int>> generate(int numRows);
};


vector<vector<int>> Solution::generate(int numRows)
{
	vector<vector<int>> yHTriangle;
	for (int row = 0; row < numRows; row++)
	{
		for (int col = 0; col < row + 1; col++)
		{
			if (col == 0 && col == row)
				yHTriangle[row][col] = 1;
			else
				yHTriangle[row][col] = yHTriangle[row - 1][col - 1] + yHTriangle[row - 1][col];
			cout << yHTriangle[row][col] << '\t';
		}
		cout << '\n';
	}

	return yHTriangle;
}


