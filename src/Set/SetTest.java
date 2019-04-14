package Set;

import java.util.ArrayList;
import java.util.Random;

public class SetTest {

    private static double testSet(Set<String> set, String filename, String filepath){

        long startTime = System.nanoTime();

        System.out.println(filename);

        ArrayList<String> word1 = new ArrayList<>();

        if(FileOperation.readFile(filepath, word1)){
            System.out.println("Total words: "  + word1.size());

            for(String word : word1)
                set.add(word);

            System.out.println("Total different words:" + set.getSize());
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    public static void BST_LinkedList_Set_Compare(){

        String name1 = "傲慢与偏见文本词汇数据统计";
        String name2 = "双城记文本词汇数据统计";

        String path1 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/Set/data/pride-and-prejudice.txt";
        String path2 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/Set/data/a-tale-of-two-cities.txt";

        BSTSet<String> bstSet1 = new BSTSet<String>();
        double time1 = testSet(bstSet1, name1, path1 );
        System.out.println("BSTset time: " + time1 + " s");
        System.out.println();

        BSTSet<String> bstSet2 = new BSTSet<String>();
        double time2 = testSet(bstSet2, name2, path2 );
        System.out.println("BSTset time: " + time2 + " s");
        System.out.println();



        LinkedListSet<String> linkedListSet1 = new LinkedListSet<String>();
        double time3 = testSet(linkedListSet1, name1, path1 );
        System.out.println("linkedListSet time: " + time3 + " s");
        System.out.println();

        LinkedListSet<String> linkedListSet2 = new LinkedListSet<String>();
        double time4 = testSet(linkedListSet2, name2, path2 );
        System.out.println("linkedListSet time: " + time4 + " s");
        System.out.println();

    }
}
