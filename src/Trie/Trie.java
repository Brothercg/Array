package Trie;

import java.util.TreeMap;

public class Trie {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    public Node root;
    public int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    // 获取Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个单词word
    public void add(String word){
        Node cur = root;

        for( int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            // c 为空，新创一个结点
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());

            // c 不为空
            cur = cur.next.get(c);
        }

        // 查询到这个位置不一定是到了叶子节点，即新插入的单词遍历到头了。

        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }

    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){
        Node cur = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            if(cur.next.get(c) == null)
                return false;

            cur = cur.next.get(c);
        }

        return cur.isWord;
    }
}
