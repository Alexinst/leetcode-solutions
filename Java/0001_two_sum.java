class MySolution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        boolean hasFound = false;
        for (int i = 0; i < nums.length; i++)
        {
            int tmp = target - nums[i];
            for (int j = i + 1; j < nums.length; j++)
            {
                if (nums[j] == tmp)
                {
                    res[0] = i;
                    res[1] = j;
                    hasFound = true;
                    break;
                }
            }
            if (hasFound == true)
                break;
        }
        return res;
    }
}
