/*
给一非空的单词列表，返回前 k 个出现次数最多的单词。

返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

示例 1：
	输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	输出: ["i", "love"]
	解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
		  注意，按字母顺序 "i" 在 "love" 之前。
 
示例 2：
	输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
	输出: ["the", "is", "sunny", "day"]
	解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
		  出现次数依次为 4, 3, 2 和 1 次。

注意：
1. 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
2. 输入的单词均由小写字母组成。

扩展练习：
	尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
*/

class Solution1 {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }        
        
        PriorityQueue<String> queue = new PriorityQueue<>(k, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (map.get(s1) == map.get(s2))
                    return s2.compareTo(s1);
                
                return map.get(s1).compareTo(map.get(s2));
            }
        });
        
        for (String key : map.keySet()) {
            if (queue.size() < k)
                queue.add(key);
            else if (queue.comparator().compare(key, queue.peek()) > 0) {
                queue.poll();
                queue.add(key);
            }
        }
        
        String[] res = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = queue.poll();
        }
        
        return Arrays.asList(res);
    }
}

import java.util.Collection;
import java.util.Collections;

class Solution {
	class WordCount {
		String word;
		int count;
	}

	public List<String> topKFrequent(String[] words, int k) {
		Map<String, WordCount> map = new HashMap<>();
		for (String word : words) {
			if(map.containsKey(word)) {
				WordCount wordCount = map.get(word);
				wordCount.count = wordCount.count + 1;
			} else {
				WordCount wordCount = new WordCount();
				wordCount.word = word;
				wordCount.count = 1;
				map.put(word, wordCount);
			}
		}
		Collection<WordCount> wordCounts = map.values();
		List<WordCount> ws = new ArrayList<>(wordCounts);
		Collections.sort(ws, new Comparator<WordCount>() {
			@Override
			public int compare(WordCount o1, WordCount o2) {
				if (o1.count < o2.count) {
					return 1;
				} else if (o1.count == o2.count) {
					return o1.word.compareTo(o2.word);
				} else {
					return -1;
				}
			}
		});
		List<String> result = new ArrayList<>();
		for(WordCount wordCount : ws) {
			if (result.size() < k) {
				result.add(wordCount.word);
			} else {
				break;
			}
		}
		return result;
	}
}

class Solution3 {
    class TrieNode {
        int count;
        char value;
        String word;
        boolean isWord = false;
        TrieNode[] children = new TrieNode[26];
        TrieNode() {
        }

        TrieNode(char value) {
            this.value = value;
        }
    }

    ArrayList<String>[] buckets = null;

    public List<String> topKFrequent(String[] words, int k) {
        TrieNode trieNode = new TrieNode(' ');
        buckets = new ArrayList[words.length];
        List<String> res = new ArrayList<>();

        for (int i = 0, len = words.length; i < len; ++i) {
            insert(trieNode, words[i]);
        }

        dfs(trieNode);

        for (int i = buckets.length - 1; i >= 0 && res.size() < k; --i) {
            ArrayList<String> tempList = buckets[i];
            if (tempList == null) continue;

            for (int j = 0, lenJ = tempList.size(); j < lenJ && res.size() < k; ++j) {
                res.add(tempList.get(j));
            }
        }

        return res;
    }

    public void insert(TrieNode node, String word) {
        char[] arrTemp = word.toCharArray();
        for (int j = 0, lenJ = arrTemp.length; j < lenJ; ++j) {
            if (node.children[arrTemp[j] - 'a'] == null)
                node.children[arrTemp[j] - 'a'] = new TrieNode(arrTemp[j]);
            node = node.children[arrTemp[j] - 'a'];
        }
        node.isWord = true;
        node.count++;
        node.word = word;
    }

    public void dfs(TrieNode node) {
        for (TrieNode tempNode : node.children) {
            if (tempNode != null && tempNode.isWord) {
                if (buckets[tempNode.count] == null)
					buckets[tempNode.count] = new ArrayList<>();

                buckets[tempNode.count].add(tempNode.word);
            }
            if (tempNode != null) {
                dfs(tempNode);    
            }
        }
    }
}
