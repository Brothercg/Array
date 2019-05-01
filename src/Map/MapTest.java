package Map;

import Utils.FileOperation;
import java.util.ArrayList;

public class MapTest {
    private static double testMap(Map<String, Integer> map, String filename, String filepath) {

        long startTime = System.nanoTime();

        System.out.println(filename);

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile(filepath, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE " + map.get("pride"));
            System.out.println("Total different PREJUDICE " + map.get("prejudice"));

        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    public static void BST_LinkedList_Map_Compare() {

        String name1 = "傲慢与偏见文本词汇数据统计";
        String name2 = "双城记文本词汇数据统计";

        String path1 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/data/pride-and-prejudice.txt";
        String path2 = "/Users/chenguang/IdeaProjects/Data-Structures/DataStruct/src/data/a-tale-of-two-cities.txt";

        BSTMap<String, Integer> bstSet1 = new BSTMap<String, Integer>();
        double time1 = testMap(bstSet1, name1, path1);
        System.out.println("BSTMap time: " + time1 + " s");
        System.out.println();

        BSTMap<String, Integer> bstSet2 = new BSTMap<String, Integer>();
        double time2 = testMap(bstSet2, name2, path2);
        System.out.println("BSTMap time: " + time2 + " s");
        System.out.println();


        LinkedListMap<String, Integer> linkedListSet1 = new LinkedListMap<String, Integer>();
        double time3 = testMap(linkedListSet1, name1, path1);
        System.out.println("LinkedListMap time: " + time3 + " s");
        System.out.println();

        LinkedListMap<String, Integer> linkedListSet2 = new LinkedListMap<String, Integer>();
        double time4 = testMap(linkedListSet2, name2, path2);
        System.out.println("LinkedListMap time: " + time4 + " s");
        System.out.println();

    }
}