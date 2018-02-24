package tree;

/**
 * 思维进入了陷阱，联想到了暴力破解，采用先序遍历，求每一个节点的路径，复杂度飙升
 * 时隔几个月第二次看还是不会(尴尬)
 * Created by gouthamvidyapradhan on 18/10/2017.
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
 * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 Example:
 Given a binary tree
  1
 / \
 2   3
 / \
 4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int max = 0;
    /**
     * Main method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(root));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    /**
     * 采用后续遍历
     * 其实还是没有领略的后续遍历的好处，其实一旦盯住某个节点时，他的左右子树已经处理完了。
     * 而左右子树的处理结果就是返回他们各自的深度。
     * @param node
     * @return
     */
    private int dfs(TreeNode node){
        if(node != null){
            int left = dfs(node.left);
            int right = dfs(node.right);
            max = Math.max(max, left + right);
            return left > right ? left + 1 : right + 1;
        }
        return 0;
    }
}
