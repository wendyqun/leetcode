package tree;

/**
 * Created by gouthamvidyapradhan on 14/08/2017.
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * <p>
 * But the following [1,2,2,null,3,null,3] is not:
 *   1
 *  / \
 * 2   2
 *  \   \
 *  3    3
 */
public class SymmetricTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean myFun(TreeNode root){
        if(root==null){
            return true;
        }
        return subSym(root.left,root.right);
    }

    public static boolean subSym(TreeNode node1,TreeNode node2){
        if(node1==null&&node2==null)
            return true;
        if(node1==null||node2==null){
            return false;
        }
        if(node1.val!=node2.val)
            return false;
        return subSym(node1.left,node2.right)&&subSym(node1.right,node2.left);
    }

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(1);
        System.out.println(new SymmetricTree().isSymmetric(node));
        System.out.println(SymmetricTree.myFun(node));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left == null || right == null) return false;
        return dfs(left.left, right.right) && left.val == right.val && dfs(left.right, right.left);
    }
}
