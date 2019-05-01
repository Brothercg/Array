package Set;

import BInarySearchTree.BST;
import Utils.FileOperation;

import java.util.ArrayList;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
         return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }


    private static void txtDataStatic(String filename, String filepath){
        System.out.println(filename);

        ArrayList<String> word1 = new ArrayList<>();

        if(FileOperation.readFile(filepath, word1)){
            System.out.println("Total words: "  + word1.size());

            BSTSet<String> set1 = new BSTSet<String>();

            for(String word : word1)
                set1.add(word);

            System.out.println("Total different words:" + set1.getSize());
        }
    }

    public static void main(String[] args) {

        //为啥子输入绝对路径才ok？
        String path1 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/Set/data/pride-and-prejudice.txt";
        String path2 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/Set/data/a-tale-of-two-cities.txt";

        txtDataStatic("傲慢与偏见文本词汇数据统计", path1);

        System.out.println();

        txtDataStatic("双城记文本词汇数据统计", path2);

    }
}
