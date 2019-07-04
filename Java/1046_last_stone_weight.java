/*
有一堆石头，每块石头的重量都是正整数。
每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

提示：
1. 1 <= stones.length <= 30
2. 1 <= stones[i] <= 1000
*/

class Solution {
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        int diff = stones[len - 1];
        for (int i = 0; i < len - 1; i++) {
            Arrays.sort(stones);
            diff = stones[len - 1] - stones[len - 2];
            diff = diff >= 0 ? diff : -diff;
            stones[len-2] = 0;
            stones[len-1] = diff;
        }
        
        return diff;
    }
}

class Solution {
     public int lastStoneWeight(int[] stones) {
     	int x, y;
     	int end = stones.length - 1;
     	Arrays.parallelSort(stones);
     	while (end > 0) {
        	x = stones[end - 1];
         	y = stones[end];
         	if (x != y) {
             	stones[end] = 1001;
             	stones[end - 1] = y-x;
             	end--;
         	}
         	else
             	end -= 2;
         	Arrays.parallelSort(stones);
     	}
        return end == 0 ? stones[end] : 0;
    }
}
