package Map;


import Utils.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next =  next;
        }

        public Node(K key){
            this(key, null, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead =  new Node();
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


    private Node getNode(K key){

        Node cur = dummyHead.next;

        while(cur != null){
            if(cur.key.equals(key))
                return cur;
            cur = cur.next;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {

        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {

        Node node = getNode(key);

        //如果数据不存在，那么在链表头部添加元素。
        if(node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }else
            node.value = value;
    }

    @Override
    public void set(K key, V newValue) {

        Node node = getNode(key);
        if(node == null)
            throw  new IllegalArgumentException(key + "doesn't exist!");

        node.value = newValue;
    }


    @Override
    public V remove(K key) {

        Node prev = dummyHead.next;

        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        //删除对应的元素
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        //如果没有对应的元素，返回空。
        return null;
    }

    public static void main(String[] args){

        String name1 = "傲慢与偏见文本词汇数据统计";
        String name2 = "双城记文本词汇数据统计";

        String path1 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/data/pride-and-prejudice.txt";
        String path2 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/data/a-tale-of-two-cities.txt";

        ArrayList<String> words = new ArrayList<>();

        if(FileOperation.readFile(path2, words)){
            System.out.println("Total words: "  + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<String, Integer>();

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
