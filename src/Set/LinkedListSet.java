package Set;

import Linklist_Recursive.LinkedList;

import java.util.ArrayList;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void add(E e) {
        if(!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    private static void txtDataStatic(String filename, String filepath){
        System.out.println(filename);

        ArrayList<String> word1 = new ArrayList<>();

        if(FileOperation.readFile(filepath, word1)){
            System.out.println("Total words: "  + word1.size());

            LinkedListSet<String> set1 = new LinkedListSet<String>();

            for(String word : word1)
                set1.add(word);

            System.out.println("Total different words:" + set1.getSize());
        }
    }

    public static void main(String[] args){
        //为啥子输入绝对路径才ok？
        String path1 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/Set/data/pride-and-prejudice.txt";
        String path2 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/Set/data/a-tale-of-two-cities.txt";

        txtDataStatic("傲慢与偏见文本词汇数据统计", path1);

        System.out.println();

        txtDataStatic("双城记文本词汇数据统计", path2);
    }
}
