/*
给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution
{
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& matrix);
};

vector<int> Solution::findDiagonalOrder(vector<vector<int>>& matrix)
{
    int rowSize, colSize, diagonalNum;
    vector<int> diagOrderNums;
    rowSize = matrix.size();                //¾ØÕóµÄÐÐÊý

    int i, j, row, col;
    if (rowSize > 0)
    {
        colSize = matrix[0].size();             //¾ØÕóµÄÁÐÊý
        diagonalNum = rowSize + colSize - 1;    //¶Ô½ÇÏßµÄÊýÁ¿
        for (i = 0; i < diagonalNum; i++)
        {
            if (i % 2 == 0)
            {
                for (j = i; j > -1; j--)
                {
                    row = j;
                    col = i - j;
                    if (row < rowSize && col < colSize)
                        diagOrderNums.push_back(matrix[row][col]);
                }
            }
            else
            {
                for (j = 0; j <= i; j++)
                {
                    row = j;
                    col = i - j;
                    if (row < rowSize && col < colSize)
                        diagOrderNums.push_back(matrix[row][col]);
                }
            }
        }
    }
    return diagOrderNums;
}
