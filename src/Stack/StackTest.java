package Stack;

import java.util.Random;

public class StackTest {
    private static double testStack(Stack<Integer> stack, int opCount){

        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0; i < opCount; i++)
            stack.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    /**
     * opCount = 100000;
     * ArrayStack, time: 0.050035297 s
     * linkedListStack, time: 0.018864557 s
     *
     * opCount = 10000000
     * ArrayStack, time: 2.795067278 s
     * linkedListStack, time: 3.936748134 s
     *
     * opCount的不同，对应的运行时间也是不一样的。具体的规律难以总结，但是原因无非以下几条：
     *
     *  1、ArrayStack在数据扩容，缩容的时候，会存在数据搬迁的问题。
     *
     *  2、linkedListStack 会存在new节点的问题。
     *
     *  3、以上两点都会会受到opCount取值大小的影响。
     *
     * @param opCount
     */
    public static void array_Linked_StackCompare(int opCount){

        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("linkedListStack, time: " + time2 + " s");
    }
}
