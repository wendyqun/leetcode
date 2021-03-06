package tree;

/**
 * Created by gouthamvidyapradhan on 30/08/2017.
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * <p>
 * Example 1:
 * Input:
 * <p>
 *  2
 * / \
 * 1   3
 * <p>
 * Output:
 * 1
 * Example 2:
 * Input:
 * <p>
 *  1
 * / \
 * 2   3
 * /   / \
 * 4   5   6
 * /
 * 7
 * <p>
 * Output:
 * 7
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
 */
public class FindBottomLeftTreeValue {
    private static int max = 0, result;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 采用中序遍历
     * @param root
     */
    public void myFun1(TreeNode root){
        f(root,0);
    }

    public void f(TreeNode root, int height){
        if(root!=null){
            f(root.left,height+1);
            if(height>max){
                max=height;
                result=root.val;
            }
            f(root.right,height+1);
        }
    }

    /**
     * 先序遍历
     * @param root
     * @param deep
     */
    public  static void myFun(TreeNode root,int deep){
        if(root==null){
            return;
        }
        deep=deep+1;
        if(deep>max){
            max=deep;
            result=root.val;
        }
        myFun(root.left,deep);
        myFun(root.right,deep);
    }

    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.left.left = new TreeNode(7);
        root.right.right = new TreeNode(6);
        root.right.right.right=new TreeNode(8);
        new FindBottomLeftTreeValue().myFun1(root);
        System.out.println(result);
        //System.out.println(new FindBottomLeftTreeValue().findBottomLeftValue(root));
    }

    public int findBottomLeftValue(TreeNode root) {
        preorder(root, 1);
        return result;
    }

    private void preorder(TreeNode node, int level) {
        if (node != null) {
            if (level > max) {
                result = node.val;
                max = level;
            }
            preorder(node.left, level + 1);
            preorder(node.right, level + 1);
        }
    }
}
