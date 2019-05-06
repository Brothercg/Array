package MaxHeap;

import Queue.Queue;

// 下面一行代码有3个E，其含义如下，我的理解哈：
// 第一个E，是为了说明"PriorityQueue"中国存放的元素是E。
// 第二个E是为了说明元素E是可比较的。
// 第三个是为了说明继承的这个接口里面的元素是E
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<E>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}
