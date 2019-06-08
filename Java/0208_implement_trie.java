/*
实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

示例:

	Trie trie = new Trie();

	trie.insert("apple");
	trie.search("apple");   // 返回 true
	trie.search("app");     // 返回 false
	trie.startsWith("app"); // 返回 true
	trie.insert("app");   
	
说明:
1. 你可以假设所有的输入都是由小写字母 a-z 构成的。
2. 保证所有输入均为非空字符串。
*/

class MyTrie {
    
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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.children.containsKey(word.charAt(i)))
                return false;
            
            node = node.children.get(word.charAt(i));
        }
        return node.isEnd == true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!node.children.containsKey(prefix.charAt(i)))
                return false;
            node = node.children.get(prefix.charAt(i));
        }
        return true;
    }
}

class Trie1 {

    /** Initialize your data structure here. */
    
    class Node{
        Node[] nodes;
        boolean isEnd;
        public Node(){
            nodes=new Node[26];
        }
    }
    
    private Node root;
    
    public Trie() {
        root=new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node t=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(t.nodes[index]==null){
                t.nodes[index]=new Node();
            }
            t=t.nodes[index];
        }
        t.isEnd=true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node t=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(t.nodes[index]==null){
               return false;
            }
            t=t.nodes[index];
        }
        return t.isEnd==true;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        Node t=root;
         for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(t.nodes[index]==null){
               return false;
            }
            t=t.nodes[index];
        }
        return true;
    }
}
