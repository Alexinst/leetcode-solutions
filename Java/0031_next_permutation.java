/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

class MySolution {
    public void nextPermutation(int[] nums) {
        // 1.从后向前查看逆序区域，找到逆序区域的第一位，也就是数字置换的边界 
        int index = -1;
        for (int i = nums.length - 2; i >= 0 ; i--) {
            if (nums[i] < nums[i+1]) {
                index = i+1;  // >= 1
                break;
            }
        }

        // 2.把逆序区域之前的一位和逆序区域中刚刚大于它的数字交换位置 
        if (index != -1) {
            for (int i = nums.length - 1; i >= index; i--) {
                if (nums[i] > nums[index-1]) {
                    int tmp = nums[index-1];
                    nums[index-1] = nums[i];
                    nums[i] = tmp;
                    break;
                }
            }
        }
        else
            index = 0;

        // 3.把原来的逆序区域调转顺序
        for (int i = index, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}

class Solution1 {
    public void nextPermutation(int[] nums) {
        // find the last pair in ascend order
        int len = nums.length;
        int index = -1;
        int aux_index = -1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                aux_index = i;
                continue;
            }
            if (index >= 0 && nums[i] > nums[index]) {
                aux_index = i;
            }
        }
        if (index >= 0) {
            exchange(index, aux_index, nums);
        }
        reverse(index + 1, len - 1, nums);
    }

    private void reverse(int start, int end, int[] nums) {
        int i = start;
        int j = end;
        while(i < j){
            exchange(i,j,nums);
            i++;
            j--;
        }
    }

    private void exchange(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
