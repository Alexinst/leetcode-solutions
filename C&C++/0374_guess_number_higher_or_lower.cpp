/*
我们正在玩一个猜数字游戏。 游戏规则如下：
我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
每次你猜错了，我会告诉你这个数字是大了还是小了。
你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：

-1 : 我的数字比较小
 1 : 我的数字比较大
 0 : 恭喜！你猜对了！

示例 :
	输入: n = 10, pick = 6
	输出: 6
*/

// Forward declaration of guess API.
// @param num, your guess
// @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
int guess(int num);

class MySolution {
public:
    int guessNumber(int n) {
        int left = 1;
        int right = n;
        // int guessedNum = ()
        while (true)
        {
            // Assign (left + right)/2 to a int will cause overflow.
            int guessed = left + (right - left) / 2;
            int res = guess(guessed);
            if (res == 0)
                return guessed;
            else if (res == -1)
                right = guessed - 1;
            else
                left = guessed + 1;
        }
    }
};

class Solution1 {
public:
    int guessNumber(int n) {
        long long int start=1,end=n,mid=(start+end)/2;
        while(guess(mid)!=0){
             if(guess(mid)==1){
                start=mid+1;
                mid=(start+end)/2;
             }
             else if(guess(mid)==-1){
                 end=mid-1;
                 mid=(start+end)/2;
             }
        }
        return mid;
    }
};
