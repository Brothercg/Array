public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，
     * @param capacity 初始容量
     */
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 无参数的构造函数，默认capacity大小
     */
    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){

        if(size == data.length){
            throw new IllegalArgumentException("Add failed. Array is full.");
        }

        if(index < 0|| index > size){
            throw new IllegalArgumentException("Add failed. Require index < 0|| index > size.");
        }

        for(int i = size -1; i >= index; i--){
            data[i + 1] = data[i];
        }

        data[index] = e;
        size ++;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public E get(int index) {

        /**
         *  capacity是数组的总容量，而size是使用的容量，这样的组合确保了那些没有用的位置（存着0的那些位置的元素不被访问。）
         */
        if(index < 0|| index > size){
            throw new IllegalArgumentException("Get failed. Require index < 0|| index > size.");
        }

        return data[index];
    }

    public boolean contains(E e){
        for(int i = 0; i < size; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 查找并返回元素下标
     * @param e
     * @return
     */
    public int find(E e){
        for(int i = 0; i < size; i++){
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    public E remove(int index){

        if(index < 0|| index > size){
            throw new IllegalArgumentException("Delete failed. Require index < 0|| index > size.");
        }

        E ret = data[index];

        for(int i = index + 1; i < size; i++){
            data[i - 1] = data[i];
        }

        size --;

        /**
         * 下面这行代码添加的原因：
         * 如果我们像原先那样只是存储int类型的数据的时候，直接不用理会这个被删除的数据就可以了，但是现在为了实现其通用性，改用"E"，那么
         * 这个位置上的元素在"size --"之后存储的就是E的对象的引用，不会被java的垃圾回收所回收。
         *
         *  = null 之后就会被回收
         */
        data[size] = null;

        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1  );
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    public void set(int index, E e){
        if(index < 0|| index > size){
            throw new IllegalArgumentException("Get failed. Require index < 0|| index > size.");
        }
        data[index] = e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: Size: = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i =0; i < size; i++){
            res.append(data[i]);
            if(i != size -1)
                res.append(", ");
        }
        res.append(']');

        return res.toString();
    }
}
