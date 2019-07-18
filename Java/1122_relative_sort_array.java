/*
给你两个数组，arr1 和 arr2，
1. arr2 中的元素各不相同
2. arr2 中的每个元素都出现在 arr1 中

对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

示例：
	输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
	输出：[2,2,2,1,4,3,3,9,6,7,19]

提示：
1. arr1.length, arr2.length <= 1000
2. 0 <= arr1[i], arr2[i] <= 1000
3. arr2 中的元素 arr2[i] 各不相同
4. arr2 中的每个元素 arr2[i] 都出现在 arr1 中

-----------------------------------------------------------------------------------------------------

Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  
Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 

Example 1:
	Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
	Output: [2,2,2,1,4,3,3,9,6,7,19]
 
Constraints:
1. arr1.length, arr2.length <= 1000
2. 0 <= arr1[i], arr2[i] <= 1000
3. Each arr2[i] is distinct.
4. Each arr2[i] is in arr1.
*/

class MySolution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr2.length == 0) {
            if (arr1.length > 1)
                Arrays.sort(arr1);
            return arr1;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[arr1.length];
        int index = 0;
        for (int key : arr2) {
            for (int j = 0; j < map.get(key); j++) {
                res[index] = key;
                index++;
            }
            map.remove(key);
        }

        int start = index;
        for (int key : map.keySet()) {
            for (int i = 0; i < map.get(key); i++) {
                res[index] = key;
                index++;
            }
        }

        Arrays.sort(res, start, res.length);
        return res;
    }
}

class Solution1 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for(int x : arr1) 
            count[x] += 1;
        
        int len = arr1.length;
        int i = 0;
        for (int x : arr2) {
            while (count[x] != 0) {
                arr1[i] = x;
                i += 1;
                count[x] -= 1;
            }
        }
        for (int i = 0; i < 1001; i++) {
            while (count[i] != 0) {
                arr1[i] = i;
                i += 1;
                count[i] -= 1;
            }

            if (i == len) break;
        }
        return arr1;
    }
}
