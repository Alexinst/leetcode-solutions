/*
 键值映射：实现一个 MapSum 类里的两个方法，insert 和 sum。

对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

示例 1:
	输入: insert("apple", 3), 输出: Null
	输入: sum("ap"), 输出: 3
	输入: insert("app", 2), 输出: Null
	输入: sum("ap"), 输出: 5
*/

class MyMapSum {
    
    class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public int sum = 0;
        public int val = 0;
        
        public TrieNode() {}
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode node = root;
        boolean isExisting = false;
        
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!node.children.containsKey(c))            
                node.children.put(c, new TrieNode());
            else {  // 键已存在
                if (i == key.length() - 1)
                    isExisting = true;
            }
            
            node = node.children.get(c);
            node.sum += val;
        }
        
        if (isExisting) {
            int lastVal = node.val;
            node = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                
                node = node.children.get(c);
                node.sum = node.sum - lastVal;
            }
        }
        
        node.val = val;
    }
    
    public int sum(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            
            if (!node.children.containsKey(c)) return 0;
            
            node = node.children.get(c);
        }
        return node.sum;
    }
    
}

class MapSum1 {

    /** Initialize your data structure here. */
    HashMap<String,Integer> m;
    public MapSum() {
        m = new HashMap<String,Integer>();
    }
    
    public void insert(String key, int val) {
        m.put(key, val);
    }
    
    public int sum(String prefix) {
    	int sum = 0;
		int len = prefix.length();
    	outfor:
    	for(String s:m.keySet()) {
    		int slen = s.length();
    		if(slen<len) continue;

    		for(int i = 0; i < len; i++) {
    			if(prefix.charAt(i) != s.charAt(i))
    				continue outfor;
    		}
    		sum += m.get(s);
    	}
    	
        return sum;
    }
}

class MapSum2 {

    HashMap<String, Integer> map;
    
    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        map.put(key,val);
    }
    
    public int sum(String prefix) {
        int count = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int len = prefix.length();
            if (key.length() < len) {
                continue;
            }
            
            if (!key.substring(0, len).equals(prefix)) {
                continue;
            }
            
            if (entry.getKey().contains(prefix)) {
                count += entry.getValue();
            }
        }
        return count;
    }
}
