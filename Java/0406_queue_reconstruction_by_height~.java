/*
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/

假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意： 总人数少于1100人。

示例
	输入: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
	输出: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

----------------------------------------------------------------------------------------------------

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note: The number of people is less than 1,100.

Example
	Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
	Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/

class Solution1 {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) return new int[0][0];
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
            }
        });
        int n = people.length;
        List<int[]> temp = new ArrayList<>();
        for (int[] p : people)
            temp.add(p[1], p);
        return temp.toArray(new int[n][2]);

    }
}
