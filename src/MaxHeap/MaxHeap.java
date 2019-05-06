package MaxHeap;

import Array.Array;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<E>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<E>(arr);
        for(int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }


    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 辅助函数，返回该节点的父亲节点的索引。
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1) / 2;
    }

    // 辅助函数 返回该及节点的左孩子。
    private int leftChild(int index) {
        return index*2 + 1;
    }

    // 辅助函数 返回该及节点的右孩子。
    private int rightChild(int index) {
        return index*2 + 2;
    }

    //向堆中添加元素,是在完全二叉树的最后的位置上添加新元素，然后从堆的添加位置上开始调整整个堆。
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    // 上浮函数，用来将堆调整成大顶堆
    // 传入的参数k是市需要开始调整到的位置。
    private void siftUp(int k){

        // 未检索到根节点且 比自身的父亲节点大。
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    // 和左右孩子孩子中元素较大的哪一个作交换。
    private void siftDown(int k) {

        //while 用这种循环比较好，表示k的左孩子都越界了，表示k已经是叶子节点的时候。
        while(leftChild(k) < data.getSize()){

            // 我这样写其实很垃圾，逻辑上饶了两层弯，这是很罗里吧嗦的写法。
//            if(data.get(leftChild(k)).compareTo(data.get(rightChild(k))) > 0 ){
//                if (data.get(k).compareTo(data.get(leftChild(k))) < 0){
//                    data.swap(k, leftChild(k));
//                    k = leftChild(k);
//                }
//            }else {
//                if (data.get(k).compareTo(data.get(rightChild(k))) < 0){
//                    data.swap(k, rightChild(k));
//                    k = rightChild(k);
//                }
//            }


            int j = leftChild(k); // j+ 1就是右子树
            if(j + 1  < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;

            if(data.get(k).compareTo(data.get(j)) >= 0)
                break;

            data.swap(k, j);
            k = j;

        }
    }

    public static void main(String[] args){
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();

        Random random = new Random();

        for(int i = 0; i < n; i ++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];

        for(int i = 0; i < n; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1; i < n; i++)
            if(arr[i - 1] < arr[i])
                throw  new IllegalArgumentException("Error");

        System.out.println("最大堆测试正确");

    }





}
