package BInarySearchTree;


import com.sun.tools.javac.file.SymbolArchive;

import java.util.*;

//树上的元素需要是可比较的
public class BST<E extends Comparable<E>>  {

    private class Node{

        private E e;
        private Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向二叉树添加加元素暴露的接口,这个1，及其相关的代码，实现方式比较啰嗦
     * 可以比对下 1和 2 的优劣。
     * @param e
     */
    public void add_1(E e){
        //如果树的根节点是空的，构造新节点。
        if(root == null){
            root = new Node(e);
            size ++;
        }else
            add_2(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法。
     * 注意，这段代码的返回值为空
     */
    private void add_1(Node node, E e){
        /**
         * 以下的判断，是一份是比较冗余的代码，对于元素e，需要比对多次，
         * 对于指针是否为空，同样需要比对多次。
         * 对于新添加的元素，当比对到某个节点为null的时候，就一定是个空元素。
         * 而以下的比对逻辑
         */

        //首先对新来的元素进行判断，判断其基于当前节点node应该放什么位置
        //1、如果元素存在树中，就不做插入，二分搜索树上没有重复的元素。
        if(e.equals(node.e))
            return;
        //把小的元素插入到左子树上。且往下递归，知道找到左子树为空的情况为止
        else if(e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size ++;
            return;
        }
        else if(e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size ++;
            return;
        }

        /**
         *  如果当前节点的左右左右子树都不为空，那就往深入去递归
         *  根据比对的大小往左右递归
         */

        if(e.compareTo(node.e) < 0)
            add_1(node.left, e);
        else //e.compareTo(node.e) > 0
            add_1(node.right, e);
    }


    /**
     * 这个一连套的2的实现方式，明显是好于1的，代码没有那么罗里吧嗦。
     * @param e
     */

    public void add(E e){
        root = add_2(root , e);
    }

    /**
     * 以node为根的二分搜索树中插入元素e,递归算法
     * 返回插入新节点后的二分搜索树的根。
     * @param node
     * @param e
     * @return
     */
    private Node  add_2(Node node, E e){
        //1、如果元素存在树中，就不做插入，二分搜索树上没有重复的元素。

        if(node == null){
            size ++;
            return new Node(e);
        }

        //根据比对的大小往左右递归
        if(e.compareTo(node.e) < 0)
            node.left = add_2(node.left, e);
        else if(e.compareTo(node.e) > 0)//e.compareTo(node.e) > 0
            node.right = add_2(node.right, e);

        //二叉搜索树不能有重复的元素，上面的if判断了> 和 < 的值，对于=，直接不处理。
        return node;
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    //以node 为根的元素
    private boolean contains(Node node, E e){

        if(node == null)
            return false;

        if(e.compareTo(node.e) == 0)
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }


    /**
     * 递归前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    /**
     * 递归中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {

        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    /**
     * 递归后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {

        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);

        System.out.println(node.e);
    }

    /**
     * 前序非递归思想：
     *  1、用一个栈来实现。
     *  2、根节点先入栈。
     *  3、栈不空的时候，栈顶出栈，并输出栈顶元素。
     *  4、在出站元素左右子树不为空的情况下，先入右子树，后入左子树。
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null)
                stack.push(cur.right);

            if(cur.left != null)
                stack.push(cur.left);

        }
    }

    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e  );

            if(cur.left != null)
                queue.add(cur.left);

            if(cur.right != null)
                queue.add(cur.right);

        }
    }

    /**
     *  1、参考经典教科书实现另外两种的非递归。
     *  2、并学习《玩转算法面试》中完全模拟系统栈的写法。
     * @return
     */








    // 寻找二分搜索树的最小元素
    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node minNode = minimum(root);
        return minNode.e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if( node.left == null )
            return node;

        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node){
        if( node.right == null )
            return node;

        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在节点
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }


    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if(node == null)
            return null;

        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else { //e == node.e 找到需要删除的节点

            //左子树为空的处理情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return  rightNode;
            }

            //右子树为空的处理情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return  leftNode;
            }

            //左右子树都不为空的处理情况
            //找到逼待删除节点大的最小节点，即待删除节点的右子树的最小节点，用此节点顶替待删除节点。
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;


        }


    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();

        //这个"----"用来表示深度
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }


    public static void main(String[] args) {

//        BST<Integer> bst = new BST<>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
//        for(int num: nums)
//            bst.add(num);
//
//        /////////////////
//        //      5      //
//        //    /   \    //
//        //   3    6    //
//        //  / \    \   //
//        // 2  4     8  //
//        /////////////////
//
//        System.out.println("前序");
//        bst.preOrder();
//        System.out.println("===============");
//
//        System.out.println("中序");
//        //二分搜索树的中序输出是有序的
//        bst.inOrder();
//        System.out.println("===============");
//
//        System.out.println("后序");
//        bst.postOrder();
//        System.out.println("===============");
//
//        System.out.println("前序非递归");
//        bst.preOrderNR();
//        System.out.println("===============");
//
//
//        System.out.println("层次遍历");
//        bst.levelOrder();
//        System.out.println("===============");

//        System.out.println();
//
//        System.out.println(bst);



        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;

        // test removeMin
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while(!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for(int i = 1 ; i < nums.size() ; i ++)
            if(nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");


    }


}
