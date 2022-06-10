// Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

// 请你实现 Trie 类：

// Trie() 初始化前缀树对象。
// void insert(String word) 向前缀树中插入字符串 word 。
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
//  

// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/implement-trie-prefix-tree
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Trie {

    class Node{
        int count;
        Node[] child;
        Node(){
            count = 0;
            child = new Node[26];
        }
    }

    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        find(word, true, false);
    }
    
    public boolean search(String word) {
        return find(word, false, false);
    }
    
    public boolean startsWith(String prefix) {
        return find(prefix, false, true);
    }

    private boolean find(String s, boolean isInsert, boolean isPrefix){
        Node curr = root;
        for(char ch : s.toCharArray()){
            if(curr.child[ch - 'a'] == null){
                if(isInsert){
                    curr.child[ch - 'a'] = new Node();
                }else{
                    return false;
                }
            }
            curr = curr.child[ch - 'a'];
        }
        if(isInsert) curr.count++;
        if(isPrefix) return true;
        return curr.count > 0;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */