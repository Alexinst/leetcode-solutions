/*
设计一个支持以下两种操作的数据结构：

	void addWord(word)
	bool search(word)

search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

示例:

	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true

说明:
	你可以假设所有单词都是由小写字母 a-z 组成的。
*/

class WordDictionary1 {
    
    class TrieNode {
        public TrieNode[] next;
        public boolean isEnd = false;
        public TrieNode() {
            next = new TrieNode[26];
        }
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next[c - 'a'] == null)
                cur.next[c - 'a'] = new TrieNode();
            
            cur = cur.next[c - 'a'];
        }
        cur.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }
    
    private boolean search(TrieNode cur, String word, int index) {
        if (index == word.length()) return cur.isEnd;
        
        char c = word.charAt(index);
        if (c != '.') {
            if (cur.next[c - 'a'] == null) 
                return false;
            cur = cur.next[c - 'a'];
            return search(cur, word, index+1);
        }
        else {
            TrieNode[] next = cur.next;
            for (int i = 0; i < 26; i++) {
                if (next[i] != null && 
                    search(next[i], word, index+1))
                    return true;
            }
            return false;
        }
    }
}

class WordDictionary2 {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TrieNode('-');
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        int length = word.length();
        TrieNode node = root;

        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            int position = c - 'a';

            if (node.children[position] == null) {
                node.children[position] = new TrieNode(c);
            }
            node = node.children[position];
        }
        node.children[26] = new TrieNode('0');
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to
     * represent any one letter.
     */
    public boolean search(String word) {
        return search(word, root);
    }

    public boolean search(String word, TrieNode node) {
        if (Objects.equals("", word)) {
            if (node.children[26] != null) {
                return true;
            } else {
                return false;
            }
        }

        int length = word.length();

        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);

            if (c == '.') {
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null) {
                        String str = word.substring(i + 1);

                        if (search(str, node.children[j])) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                int position = c - 'a';

                if (node.children[position] == null) {
                    return false;
                } else {
                    node = node.children[position];
                }
            }

            if (i == length - 1) {
                if (node.children[26] != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private class TrieNode {
        char val;
        TrieNode[] children;

        public TrieNode(char val) {
            this.val = val;
            children = new TrieNode[27];
        }
    }
}
