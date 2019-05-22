class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1)
            return true;
        
        int left = 1, right = num / 2;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (num / mid == mid)
                return true;
            else if (num / mid < mid)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;
    }
}
