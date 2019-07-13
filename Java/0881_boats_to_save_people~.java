/*
第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

返回载到每一个人所需的最小船数。(保证每个人都能被船载)。

示例 1：
	输入：people = [1,2], limit = 3
	输出：1
	解释：1 艘船载 (1, 2)

示例 2：
	输入：people = [3,2,2,1], limit = 3
	输出：3
	解释：3 艘船分别载 (1, 2), (2) 和 (3)

示例 3：
	输入：people = [3,5,3,4], limit = 5
	输出：4
	解释：4 艘船分别载 (3), (3), (4), (5)

提示：
1. 1 <= people.length <= 50000
2. 1 <= people[i] <= limit <= 30000

-----------------------------------------------------------------------------------------------------------------------

The i-th person has weight people[i], and each boat can carry a maximum weight of limit. Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)

Example 1:
	Input: people = [1,2], limit = 3
	Output: 1
	Explanation: 1 boat (1, 2)

Example 2:
	Input: people = [3,2,2,1], limit = 3
	Output: 3
	Explanation: 3 boats (1, 2), (2) and (3)

Example 3:
	Input: people = [3,5,3,4], limit = 5
	Output: 4
	Explanation: 4 boats (3), (3), (4), (5)

Note:
1. 1 <= people.length <= 50000
2. 1 <= people[i] <= limit <= 30000

Solution:
https://leetcode-cn.com/problems/boats-to-save-people/solution/jiu-sheng-ting-by-leetcode/
*/

class Solution1 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int nBoat = 0, i = 0, j = people.length - 1;
    
        while (i <= j) {
            if (people[i] + people[j] <= limit)
                i++;
            j--;
            nBoat++;
        }
        
        return nBoat;
    }
}


class Solution2 {
    public int numRescueBoats(int[] people, int limit) {
        int[] bucket = new int[limit + 1];
        for (int p : people) {
            ++bucket[p];
        }
        int res = 0;
        int i = 0, j = limit;
        while (i <= j) {
            while (i <= j && bucket[i] <= 0) {
                ++i;
            }
            while (i <= j && bucket[j] <= 0) {
                --j;
            }
            if (bucket[i] <= 0 && bucket[j] <= 0) {
                break;
            }
            if (i + j <= limit) {
                --bucket[i];
            }
            --bucket[j];
            ++res;
        }
        return res;
    }
}
