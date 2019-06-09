/*

*/

class MySolution {
    public String replaceWords(List<String> dict, String sentence) {
        Trie roots = new Trie();
        for (String root : dict) {
            roots.insert(root);
        }

        String[] words = sentence.split(" ");
        String res = "";
        for (String word : words) {
            int len = roots.lenOfRoot(word);
            if (len != -1)
                word = word.substring(0, len + 1);
            res += word + " ";
        }

        return res.trim();
    }
}

class Trie {

    class TrieNode {
        public Map<Character, TrieNode> children;
        public boolean isEnd = false;
        public TrieNode() {
            children = new HashMap<>();
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c))
                node.children.put(c, new TrieNode());

            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    public int lenOfRoot(String word) {
        TrieNode node = root;
        int len = -1;
        for (int i = 0; i < word.length(); i++) {
            if (!node.children.containsKey(word.charAt(i))) return -1;

            node = node.children.get(word.charAt(i));
            if (node.isEnd) {
                len = i;
                break;
            }
        }
        return len;
    }
}

class Solution1 {
    public String replaceWords(List<String> dict, String sentence) {
        Node root = new Node();
        for(String t : dict) root.builder(t);

        StringBuilder res = new StringBuilder();
        int j = 0, i = 0;
        Node cur = root;
        int len = sentence.length();
        while(i < len){
            if (cur.child[sentence.charAt(i) - 'a'] == null) {
                while (i < len && sentence.charAt(i) != ' ') i++;

                res.append(sentence.substring(j, i));
                res.append(" ");
                j = ++i;
                cur = root;
                continue;
            }

            cur = cur.child[sentence.charAt(i) - 'a'];
            if (i + 1 == len || sentence.charAt(i + 1) == ' ' || cur.isEnd) {
                res.append(sentence.substring(j, i + 1));
                res.append(" ");
                cur = root;

                while (i < len && sentence.charAt(i) != ' ') i++;

                j = i + 1;
            }
            i++;
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }
    
    class Node{
        Node child[];
        boolean isEnd = false;
        
	Node() {
            child = new Node[26];
        }

        public void builder(String s) {
            Node tmp = this;
            for (char ch : s.toCharArray()) {
                if (tmp.child[ch - 'a'] == null)
                    tmp.child[ch - 'a'] = new Node();
                tmp = tmp.child[ch - 'a'];
            }
            tmp.isEnd = true;
        }
    }
}
