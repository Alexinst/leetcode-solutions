/*
公司计划面试 2N 人。第 i 人飞往 A 市的费用为 costs[i][0]，飞往 B 市的费用为 costs[i][1]。
返回将每个人都飞到某座城市的最低费用，要求每个城市都有 N 人抵达。

示例：
	输入：[[10,20],[30,200],[400,50],[30,20]]
	输出：110
	解释：
	第一个人去 A 市，费用为 10。
	第二个人去 A 市，费用为 30。
	第三个人去 B 市，费用为 50。
	第四个人去 B 市，费用为 20。

	最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。

提示：
1. 1 <= costs.length <= 100
2. costs.length 为偶数
3. 1 <= costs[i][0], costs[i][1] <= 1000
*/

class MySolution {
    public int twoCitySchedCost(int[][] costs) {
        int doubleN = costs.length;
        FlightCost[] fcs = new FlightCost[doubleN];
        for (int i = 0; i < doubleN; i++) {
            fcs[i] = new FlightCost(costs[i][0], costs[i][1]);
        }

        Arrays.sort(fcs);
        int minCost = 0;
        for (int i = 0; i < doubleN; i++) {
            if (i < doubleN / 2)
                minCost += fcs[i].costA;
            else
                minCost += fcs[i].costB;
        }

        return minCost;
    }

    class FlightCost implements Comparable {
        public int costA;
        public int costB;
        public int diff;

        public FlightCost(int costA, int costB) {
            this.costA = costA;
            this.costB = costB;
            this.diff = costA - costB;
        }

        @Override
        public int compareTo(Object fc) {
            return this.diff - ((FlightCost) fc).diff;
        }
    }
}

class Solution1 {
    public int twoCitySchedCost(int[][] costs) {
        int length = costs.length;
        int[] temp = new int[length];
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += costs[i][0];
            temp[i] = costs[i][0] - costs[i][1];
        }
        Arrays.sort(temp);
        for (int i = length -1; i >= length / 2; i--) {
            sum -= temp[i];
        }
        return sum;
    }
}
