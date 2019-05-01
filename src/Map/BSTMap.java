package Map;

import Utils.FileOperation;
import com.sun.tools.javac.file.SymbolArchive;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //向以node为根节点的树中插入元素，插入元素的结构为（key , Value）,递归算法
    //返回插入新节点后的树的根节点。
    private Node  add(Node node,  K key, V value){
        //1、如果元素存在树中，就不做插入，二分搜索树上没有重复的元素。

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        //根据比对的大小往左右递归
        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)//e.compareTo(node.e) > 0
            node.right = add(node.right, key, value);
        else if(key.compareTo(node.key) == 0)
            node.value = value;

        //二叉搜索树不能有重复的元素，上面的if判断了> 和 < 的值，对于=，直接不处理。
        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点。
    private Node getNode(Node node, K key){
        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else
            return getNode(node.right, key);
    }

    @Override
    public boolean contains (K key){
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }


    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "does't exist");
        node.value = newValue;
    }

    // 返回以node为最小值的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根节点的二分搜索树中的最小节点
    // 返回删除后的新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }

    // 从以二分搜索树实现的Map中删除以key为键的节点。
    public V remove(K key){

        Node node = getNode(root, key);

        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除掉以node为根的二分搜索树的中键为key的节点，递归算法
    // 返回删除后的二分搜索树的根
    private Node remove(Node node, K key) {
        if(node == null)
            return null;

        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }else { //e == node.e 找到需要删除的节点

            //左子树为空的处理情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return  rightNode;
            }

            //右子树为空的处理情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return  leftNode;
            }

            //左右子树都不为空的处理情况
            //找到删除节点大的最小节点，即待删除节点的右子树的最小节点，用此节点顶替待删除节点。
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;

        }
    }

    public static void main(String[] args){

        String name1 = "傲慢与偏见文本词汇数据统计";
        String name2 = "双城记文本词汇数据统计";

        String path1 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/data/pride-and-prejudice.txt";
        String path2 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/data/a-tale-of-two-cities.txt";

        ArrayList<String> words = new ArrayList<>();

        if(FileOperation.readFile(path1, words)){
            System.out.println("Total words: "  + words.size());

            BSTMap<String, Integer> map = new BSTMap<String, Integer>();

            for(String word : words){
                if(map.contains(word)){
                    map.set(word, map.get(word) + 1);
                }else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE " + map.get("pride"));
            System.out.println("Total different PREJUDICE " + map.get("prejudice"));

        }
    }
}
