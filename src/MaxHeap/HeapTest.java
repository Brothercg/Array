package MaxHeap;

import java.util.Random;

public class HeapTest {

    private static double testHeap(Integer[] testData, boolean isHeapify){

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify)
            maxHeap = new MaxHeap<Integer>(testData);
        else{
            maxHeap = new MaxHeap<Integer>();
            for(int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < testData.length ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();


        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int n = 1000000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for(int i = 0 ; i < n ; i ++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        //这种情况下是对整个data数组进行建堆，需要遍历树的所有节点。每个节点都需要调整。
        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        // 这个是动态建堆，data来一个元素便从新更新一次堆，相当于动态舰队，问题就少很多。
        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
