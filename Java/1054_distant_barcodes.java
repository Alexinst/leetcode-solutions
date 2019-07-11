/*
在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。

请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。

示例 1：
	输入：[1,1,1,2,2,2]
	输出：[2,1,2,1,2,1]

示例 2：
	输入：[1,1,1,1,2,2,3,3]
	输出：[1,3,1,3,2,1,2,1]

提示：
1. 1 <= barcodes.length <= 10000
2. 1 <= barcodes[i] <= 10000
*/

class MySolution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = barcodes.length, num = barcodes[0];

        // 统计数字的出现次数
        for (int barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0) + 1);
            if (map.get(barcode) > map.get(num))
                num = barcode;
        }

        // 偶数索引位填入出现次数最多的数
        int[] ans = new int[len];
        int indexAns = 0;
        for (int count = 0; count < map.get(num); count++) {
            ans[indexAns] = num;
            indexAns += 2;
        }


        // 新建数组保存尚未使用的数
        int[] tmp = new int[len - map.get(num)];
        int start = 0;
        for (int key : map.keySet()) {
            if (key == num) continue;

            for (int i = start; i < start + map.get(key); i++)
                tmp[i] = key;

            start += map.get(key);
        }

        int indexTmp = 0;
        // 补齐剩余偶数索引位
        for (; indexAns < len; indexAns += 2) {
            ans[indexAns] = tmp[indexTmp];
            indexTmp++;
        }
        // 填充奇数索引位
        for (indexAns = 1; indexAns < len; indexAns += 2) {
            ans[indexAns] = tmp[indexTmp];
            indexTmp++;
        }

        return ans;
    }
}

class Solution1 {
    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes.length <= 1) return barcodes;
        boolean flag = false;
        for (int i = 1; i < barcodes.length; i++) {
            if (barcodes[i] == barcodes[i-1]) {
                int j = i + 1;
                while (j < barcodes.length && barcodes[j] == barcodes[i]){
                    j++;
                }
                if (j < barcodes.length) {
                    int temp = barcodes[i];
                    barcodes[i] = barcodes[j];
                    barcodes[j] = temp;
                } else {
                    flag = true;
                }
            }
        }
        if (flag){
            for (int i = barcodes.length-2; i >= 0; i--){
                if (barcodes[i] == barcodes[i+1]) {
                    int j = i - 1;
                    while (j >= 0 && barcodes[j] == barcodes[i]) {
                        j--;
                    }
                    if (j >= 0) {
                        int temp = barcodes[i];
                        barcodes[i] = barcodes[j];
                        barcodes[j] = temp;
                    }
                }
            }
        }
        return barcodes;
    }
}
