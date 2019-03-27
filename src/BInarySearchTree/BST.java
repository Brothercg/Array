package BInarySearchTree;


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

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        bst.preOrder();
        System.out.println();

        System.out.println(bst);
    }


}
