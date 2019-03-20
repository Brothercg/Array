/**
 * This class is the Solution of LeetCode no.203, it use two way to slove the problem.
 *
 * The first way:
 *
 * The second way: Use the linklist with dummyHead node.
 *
 */
public class Solution {

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

        ListNode prev = dummyHead;

        while(prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else
                prev = prev.next;

        }
        return dummyHead.next;
    }


    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements_2(head, 6);
        System.out.println(res);
    }
}
