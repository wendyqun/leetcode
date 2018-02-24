package tree;

/**
 * 时隔两月，又忘了
 * Flatten['flæt(ə)n] 变平 平坦
 * Created by gouthamvidyapradhan on 04/07/2017.
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example,
 * Given
 * <p>
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * <p>
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * <p>
 * Solution: Do a pre-order traversal and maintain head and tail of a linked list at each recursive step.
 * i. Join the current node to the head of the left sub-list to form the current node as the new head.
 * ii. Join the tail of the left sub-list to the head of the right sub-list.
 * iii. Mark the left of the current node as null
 */
public class FlattenBinaryTree {

    /**
     * Class to keep track of head and tail
     */
    private class LinkNode {
        TreeNode head;
        TreeNode tail;

        LinkNode(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 1）将左子树挂到根节点的右子树上，右子树的root先保存在临时变量
     * 2）然后递归处理根节点的右子树
     * 3）处理完右子树（原左子树）后找到右子树最右端的节点（通过while循环）
     * 4）将临时节点挂到最右端的树上，再次递归处理临时节点所在的子树
     * @param root
     */
    public void myFun(TreeNode root){
        if(root==null)
            return;
        TreeNode right=root.right;
        root.right=root.left;
        root.left=null;
        myFun(root.right);
        TreeNode t=root;
        while(t.right!=null){
            t=t.right;
        }
        t.right=right;
        myFun(t.right);
    }

    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right=new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right=new TreeNode(5);
        root.right.right=new TreeNode(6);
        new FlattenBinaryTree().flatten(root);
        /*while(root!=null){
            System.out.println(root.val);
            root=root.right;
        }*/
        //=====================================//
        TreeNode r=new TreeNode(1);
        r.left=new TreeNode(2);
        r.right=new TreeNode(5);
        r.left.left=new TreeNode(3);
        r.left.right=new TreeNode(4);
        r.right.right=new TreeNode(6);
        new FlattenBinaryTree().postOrder(r);
        while(r!=null){
            System.out.println(r.val);
            r=r.right;
        }
        //=====================================//

    }

    public void flatten(TreeNode root) {
        preOrder(root);
    }

    private LinkNode preOrder(TreeNode node) {
        if (node == null) return null;
        LinkNode left = preOrder(node.left);
        LinkNode right = preOrder(node.right);
        LinkNode lNode;
        if (left == null && right == null) {
            lNode = new LinkNode(node, node);
        } else if (left == null) {
            node.right = right.head;
            lNode = new LinkNode(node, right.tail);
        } else if (right == null) {
            node.right = left.head;
            lNode = new LinkNode(node, left.tail);
        } else {
            node.right = left.head;
            left.tail.right = right.head;
            lNode = new LinkNode(node, right.tail);
        }
        node.left = null;
        return lNode;
    }

    /**
     * 采用后续遍历，先将左右子节点处理平整
     * 最后，将左子树的最后一个节点与右子树的第一个节点焊接
     * 还是这个好理解
     * @param node
     * @return
     */
    public TreeNode postOrder(TreeNode node) {
        if (node == null) return null;
        TreeNode left = postOrder(node.left);
        TreeNode right = postOrder(node.right);
        if(left!=null){
            node.right=left;
            node.left=null;
            TreeNode bottomRight=left;
            while(bottomRight.right!=null){
                bottomRight=bottomRight.right;
            }
            bottomRight.right=right;
        }
        return node;
    }

}
