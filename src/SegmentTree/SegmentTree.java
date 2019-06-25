package SegmentTree;


public class SegmentTree<E> {


    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[]) new Object[arr.length];

        for(int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];
        buildSegmenTree(0, 0, data.length - 1 );
    }

    //在treeIndex的位置表示区间【l，r】的线段树
    private void buildSegmenTree(int treeIndex, int l , int r) {
        if( l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        buildSegmenTree(leftTreeIndex, l, mid);
        buildSegmenTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] =  merger.merge(tree[leftTreeIndex] ,tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    private int leftChild(int index){
        return 2*index + 1;
    }

    private int rightChild(int index){
        return 2*index + 2;
    }

    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index out of range.");
        return query(0, 0,data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if(l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r ,queryL, queryR);
        else if(queryR <= mid + 1)
            return query(leftTreeIndex, 0, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, 0, mid ,queryL, mid);

        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index id illegal");

        data[index] = e;

        set(0, 0, data.length - 1, index, e);
    }

    // 将以treeIndex为根节点的线段树中，将index的值更新为e。
    private void set(int treeIndex, int l , int r, int index, E e) {

        if( l == r){
            tree[index] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else
            set(leftTreeIndex, l, mid, index,e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');

        for(int i =0; i < tree.length; i++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }

        res.append(']');
        return res.toString();
    }

    public static void main(String[] args){
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

//        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, new Merger<Integer>() {
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                return a + b;
//            }
//        });

        // 可以用lambda表达式实现
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
//        System.out.println(segmentTree);

        System.out.println(segmentTree.query(0,2));

    }
}
