package tree;

/**
 * Created by gouthamvidyapradhan on 07/07/2017.
 * Given two non-empty binary trees s and t,
 * check whether tree t has exactly the same structure and node values with a subtree of s.
 * A subtree of s is a tree consists of a node in s and all of this node's descendants.
 * The tree s could also be considered as a subtree of itself.
 * <p>
 * Example 1:
 * Given tree s:
 * <p>
 *     3
 *    / \
 *   4   5
 *  / \
 * 1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 * <p>
 *     3
 *    / \
 *   4   5
 *  / \
 * 1   2
 * /
 *0
 * Given tree t:
 * 4
 * / \
 * 1   2
 * Return false.
 */
public class SubtreeOfAnotherTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) throws Exception {
        TreeNode s=new TreeNode(3);
        s.left=new TreeNode(4);
        s.right=new TreeNode(5);
        s.left.left=new TreeNode(1);
        s.left.right=new TreeNode(2);
        s.left.right.left=new TreeNode(0);

        TreeNode t=new TreeNode(4);
        t.left=new TreeNode(1);
        t.right=new TreeNode(2);

        System.out.println(SubtreeOfAnotherTree.myFun(s, t));

    }

    /**
     * 思路很简单,首先对s 先序遍历
     * 对于遍历过程中的每一个节点，都认为是与t 对标的root节点，
     * 然后以此节点为起点做为子树，再与t树进行对比
     * 注意：按照题目要求，t树的叶子节点的左右孩子节点也要参与对比
     * @param s
     * @param t
     * @return
     */
    public static boolean myFun(TreeNode s,TreeNode t){
        if(s==null)
            return false;
        return sub(s,t)||sub(s.left,t)||sub(s.right,t);
    }
    public static boolean sub(TreeNode s,TreeNode t){
        if(s==null&&t==null)
            return true;
        if(s==null||t==null)
            return false;
        return s.val==t.val&&sub(s.left,t.left)&&sub(s.right,t.right);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s != null) {
            if (s.val == t.val) {
                if (equal(s, t))
                    return true;
                else return (isSubtree(s.left, t) || isSubtree(s.right, t));
            } else return (isSubtree(s.left, t) || isSubtree(s.right, t));
        }
        return false;
    }

    private boolean equal(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        else if (s == null || t == null)
            return false;
        else if (s.val != t.val) return false;
        else return equal(s.left, t.left) && equal(s.right, t.right);
    }

}
