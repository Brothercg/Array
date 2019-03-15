public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front,tail;
    private int size;

    public LoopQueue(int capacity){
        data =(E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size ;
    }

    @Override
    public void enqueue(E e) {
        if((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;

        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {

        if(isEmpty())
            throw new IllegalArgumentException("Connot dequeue from an empty queue");

        E ret = data[front];
        data[front] = null;

        front = (front + 1) % data.length;
        size --;

        //缩容
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    private void resize(int newCapacity){

        /**
         * 原来的队列中有size个元素需要搬迁。
         */
        E[] newData =(E[]) new Object[newCapacity + 1];
        for(int i = 0; i < size; i ++){

            //tail = (tail + 1) % data.length; 所以要这么写
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: Size: = %d , capacity = %d\n", size, getCapacity()));
        res.append("Front [");
        for(int i = front; i != tail; i = (i + 1) % data.length){
            res.append(data[i]);
            if( (i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail ");

        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();

        for(int i = 0; i < 10; i++){
            loopQueue.enqueue(i);

            System.out.println(loopQueue);

            if( i % 3 == 2){
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }
    }
}
