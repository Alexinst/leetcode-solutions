/*
不使用任何内建的哈希表库设计一个哈希集合
具体地说，你的设计应该包含以下的功能
	add(value)：向哈希集合中插入一个值。
	contains(value) ：返回哈希集合中是否存在这个值。
	remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

示例:
	MyHashSet hashSet = new MyHashSet();
	hashSet.add(1);         
	hashSet.add(2);         
	hashSet.contains(1);    // 返回 true
	hashSet.contains(3);    // 返回 false (未找到)
	hashSet.add(2);          
	hashSet.contains(2);    // 返回 true
	hashSet.remove(2);          
	hashSet.contains(2);    // 返回  false (已经被删除)

注意：
1. 所有的值都在 [1, 1000000]的范围内。
2. 操作的总数目在[1, 10000]范围内。
3. 不要使用内建的哈希集合库。
*/

class MyHashSet {
public:
    /** Initialize your data structure here. */
    MyHashSet() {
        hashSet = vector<bool>(1000001, false);
    }
    
    void add(int key) {
        hashSet[key] = true;
    }
    
    void remove(int key) {
        hashSet[key] = false;
    }
    
    /** Returns true if this set contains the specified element */
    bool contains(int key) {
        return hashSet[key];
    }
private:
    vector<bool> hashSet;
};

class MyHashSet1 {
public:
    int s[100000];
    /** Initialize your data structure here. */
    MyHashSet() {
        memset(s,0,sizeof(s));
    }
    
    void add(int key) {
        s[key]++;
    }
    
    void remove(int key) {
        s[key]=0;
    }
    
    /** Returns true if this set contains the specified element */
    bool contains(int key) {
        return s[key];
    }
};
