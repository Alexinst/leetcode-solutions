/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:
	输入: [1,8,6,2,5,4,8,3,7]
	输出: 49

*/

class MySolution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if (area > max) max = area;
            }
        }
        return max;
    }
}


class Solution1 {
    public int maxArea(int[] height) {
      int left = 0;
      int right = height.length - 1;
      int max = 0;
      while (right > left) {
          int area = Math.min(height[left],height[right]) * (right-left);
          if (area > max)
            max = area;
          if (height[left]>height[right])
            right--;
          else
            left++;
      }
      return max;

    }
}

class Solution2 {
    public int maxArea(int[] height) {
    	int s= Integer.min(height[height.length-1], height[0]);
    	int max=s*(height.length-1);
    	
    	for(int l=0, r= height.length-1; l<r;) {
    		while(height[l]<=s && l<r) {
    			l++;
    		}
    		while(height[r]<=s && l<r) {
    			r--;
    		}
    		s= Integer.min(height[l], height[r]);
    		max=Integer.max(max, s*(r-l));
    		
    	}
    	return max;
    }
}
