/*
给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。

若无答案，则返回空字符串。

示例 1:
	输入: words = ["w","wo","wor","worl", "world"]
	输出: "world"
	解释: 单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。

示例 2:
	输入: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
	输出: "apple"
	解释: "apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。

注意:
1. 所有输入的字符串都只包含小写字母。
2. words数组长度范围为[1,1000]。
3. words[i]的长度范围为[1,30]。
*/

class MySolution {
    class TrieNode {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }
    }

    TrieNode root = new TrieNode();

    public String longestWord(String[] words) {
        Arrays.sort(words);

        String str = "";
        for (int i = 0; i < words.length; i++) {
            if (oneCharLonger(words[i]) && words[i].length() > str.length())
                str = words[i];
        }

        return str;
    }

    public boolean oneCharLonger(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length() - 1; i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c))
                return false;

            node = node.get(c);
        }
        node.put(word.charAt(word.length() - 1), new TrieNode());

        return true;
    }
}

class Solution1 {
    public String longestWord(String[] words) {
        Set<String> s = new HashSet<>();
        for (String word : words) {
            s.add(word);
        }
            
        int ml = 0;
        String mw = "";
        for (String w : words) {
            if (w.length() < ml) continue;
            if (w.length() == ml && w.compareTo(mw) >= 0) continue;
            int n = w.length();
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if(!s.contains(w.substring(0, i))){
                    flag = false;
                    break;
                }   
            }
            if (flag) {
                ml = w.length();
                mw = w;
            }    
        }
        
        return mw;
    }
}
