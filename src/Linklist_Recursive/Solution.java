package Linklist_Recursive;

/**
 * This class is the Solution of LeetCode no.203, it use two way to slove the problem.
 *
 * The first way:
 *
 * The second way: Use the linklist with dummyHead node.
 *
 */
public class Solution {


    public ListNode removeElements_debug(ListNode head, int val, int depth) {

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if(head == null){
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements_debug(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if(head.val == val)
            ret = res;
        else{
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }

    /**
     * Use the linklist without dummyHead node.
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements_1(ListNode head, int val){

        //delete all the head of linklist  which node.val = val
        while (head != null && head .val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if(head == null)
            return null;

        ListNode prev = head;

        // the part after the linklist
        while(prev.next != null){
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null;
            }else
                prev = prev.next;

        }
        return head;
    }


    /**
     * Use the linklist with dummyHead node.
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements_2(ListNode head, int val){

        // use dummyHead node.
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        //带头结点的单链表head指向的数据项为空
        ListNode prev = dummyHead;

        while(prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else
                prev = prev.next;

        }
        return dummyHead.next;
    }

    /**
     * 运用递归的方式解决问题
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements_3(ListNode head, int val){

        if(head == null)
            return null;

        head.next = removeElements_3(head.next, val);

        //可用三目运算符
        if(head.val == val)
            return head.next;
        else {
            /**
             * head 其实也是个节点，java没有指针形式的变量。
             */
//            head.next = res;
            return head;
        }
    }


    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements_debug(head, 6, 0);
        System.out.println(res);
    }
}
