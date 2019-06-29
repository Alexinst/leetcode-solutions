/*
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:
	返回的下标值（index1 和 index2）不是从零开始的。
	你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
	
示例:
	输入: numbers = [2, 7, 11, 15], target = 9
	输出: [1,2]
	解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
*/

class MySolution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int halfOfTar = target / 2 + 1;
        vector<int>::iterator ptr1 = numbers.begin();
        vector<int>::iterator ptr2;
        while (*ptr1 < halfOfTar)
        {
            if (*ptr1 + numbers.back() < target)
            {
                ptr1++;
                continue;
            }
            
            int num2 = target - *ptr1;
            ptr2 = find(ptr1 + 1, numbers.end(), num2);
            if (ptr2 != numbers.end())
                break;
            else
            {
                ptr1++;
                continue;
            }
        }
        int index1 = ptr1 - numbers.begin() + 1;
        int index2 = ptr2 - numbers.begin() + 1;
        vector<int> indices;
        indices.push_back(index1);
        indices.push_back(index2);
        
        return indices;
    }
};
