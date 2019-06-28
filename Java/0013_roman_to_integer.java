/*
罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
	字符          数值
	I             1
	V             5
	X             10
	L             50
	C             100
	D  	           500
	M             1000

例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
	I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
	X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
	C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

示例 1:
	输入: "III"
	输出: 3

示例 2:
	输入: "IV"
	输出: 4

示例 3:
	输入: "IX"
	输出: 9

示例 4:
	输入: "LVIII"
	输出: 58
	解释: L = 50, V= 5, III = 3.

示例 5:
	输入: "MCMXCIV"
	输出: 1994
	解释: M = 1000, CM = 900, XC = 90, IV = 4.
*/

class MySolution {
    public int romanToInt(String s) {
        int num = 0;
        
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            char cNext = s.charAt(i + 1);
            if (c == 'I' && (cNext == 'V' || cNext == 'X')) {
                num += -1;
                continue;
            }
            if (c == 'X' && (cNext == 'L' || cNext == 'C')) {
                num += -10;
                continue;
            }
            if (c == 'C' && (cNext == 'D' || cNext == 'M')) {
                num += -100;
                continue;
            }
            
            num += convert(c);
        }
        num += convert(s.charAt(s.length()-1));
        return num;
    }
    
    private int convert(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default : return 0;
        }
    }
}

class Solution1 {
    public int romanToInt(String s) {
        int sum = 0;
        for(int i = 0;i < s.length();i++){
            switch(s.charAt(i)){
                case 'I': sum = sum + 1;break;
                case 'V': sum = sum + 5;break;
                case 'X': sum = sum + 10;break;
                case 'L': sum = sum + 50;break;
                case 'C': sum = sum + 100;break;
                case 'D': sum = sum + 500;break;
                case 'M': sum = sum + 1000;break;
                default:System.out.println("违规字符");break;
            }
            if(i!=0){
               if(((s.charAt(i)=='V')||(s.charAt(i)=='X'))&&(s.charAt(i-1)=='I')) 
					sum = sum-1*2;
				if(((s.charAt(i)=='L')||(s.charAt(i)=='C'))&&(s.charAt(i-1)=='X'))
					sum = sum-10*2;
				if(((s.charAt(i)=='D')||(s.charAt(i)=='M'))&&(s.charAt(i-1)=='C'))
					sum = sum-100*2;
            }
        }
        return sum;
    }
}
