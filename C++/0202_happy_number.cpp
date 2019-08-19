/*
编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 
	输入: 19
	输出: true
	解释: 
		1^2 + 9^2 = 82
		8^2 + 2^2 = 68
		6^2 + 8^2 = 100
		1^2 + 0^2 + 0^2 = 1
*/

class MySolution {
public:
	bool isHappy(int n) {
		std::set<int> s;
		while (true)
		{
			n = turn(n);
			if (s.count(n) > 0)
				return false;
			if (n == 1)
				return true;
			s.insert(n);
		}
	}

	int turn(int n)
	{
		int sum = 0;
		while (true)
		{
			sum += (n % 10) * (n % 10);
			n = n / 10;
			if (n == 0)
				return sum;
		}

	}
};

class Solution1 {
public:
    bool isHappy(int n) {     
        
        set<int> result = {};
        while(1)
        {
            int sum = 0;
            while (n > 0)
            {
                sum += (n%10)*(n%10);
                n = n/10;
            }
            if (sum == 1)
                return true;
            if (result.find(sum) != result.end())
                return false;    
            result.insert(sum);
            n = sum;
        }
    }
};
