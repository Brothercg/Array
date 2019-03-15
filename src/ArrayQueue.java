public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    /**
     * 用户可以估计所需队列的大小
     * @param capacity
     */
    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    /**
     * 用户无法估计自己所需队列的大小。
     */
    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    /**
     * 基于数组实现的队列，出队的时间复杂度是O(n),删除的是队首的元素，
     * 即底层数组中下标为0的位置的元素，那么，数组中所有的元素都要往前移动，
     * 这部分的时间复杂度是O(n)。
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();

        res.append("Queue: ");
        res.append("front [");

        for(int i = 0; i < array.getSize(); i++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }

        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for(int i = 0; i < 10000; i++){
            queue.enqueue(i);

            System.out.println(queue);

            if( i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }


    }
}
