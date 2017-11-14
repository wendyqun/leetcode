package tree;

/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * 2
 * / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * Example 2:
 * 1
 * / \
 * 2   3
 * Binary tree [1,2,3], return false.
 */
public class ValidBinarySearchTree {
    class Range {
        long low, high;
    }

    static class Data{
        long data;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 使用后续遍历
     * 左子树的最大值要小于根节点
     * 右子树的最小值要大于根节点
     *
     * 叶子节点的最大值和最小值是他本身，叶子节点不进入递归.
     *
     * 函数有两个返回值，一个是boolean ，一个是使用Data 借助返回本子树的最大值或者最小值（根据left参数判断）
     * @param node
     * @param data
     * @param left
     * @return
     */
    static boolean myFun(TreeNode node,Data data,boolean left){
        if(node==null){
            return true;
        }
        if(node.left==null&&node.right==null){
            data.data=node.val;
            return true;
        }

        Data leftV=new Data();
        Data rightV=new Data();
        boolean b1;
        boolean b2;
        if(node.left==null){
            b1=myFun(node.right,rightV,false);
            data.data=left?node.right.val:node.val;
            return b1&&rightV.data>node.val;
        }else if(node.right==null){
            b1=myFun(node.left,leftV,true);
            data.data=left?node.left.val:node.val;
            return b1&&leftV.data<node.val;
        }else{
            b1=myFun(node.left,leftV,true);
            b2=myFun(node.right,rightV,false);
            data.data=left?node.right.val:node.left.val;
            return b1&&b2&&leftV.data<node.val&&rightV.data>node.val;
        }
    }



    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        System.out.println(new ValidBinarySearchTree().isValidBST(root));

        TreeNode node=new TreeNode(11);
        node.left = new TreeNode(3);
        node.right=new TreeNode(20);
        node.left.left=new TreeNode(1);
        node.left.right=new TreeNode(12);
        node.right.left=new TreeNode(15);
        node.right.right=new TreeNode(30);

        System.out.println(myFun(node,new Data(),true));

    }

    private boolean isValidBST(TreeNode root) {
        if (root == null ||
                (root.right == null && root.left == null)) return true;
        Range range = new Range();
        range.high = Long.MAX_VALUE;
        range.low = Long.MIN_VALUE;
        return validate(root, range);
    }

    /**
     * 先序遍历，从上向下
     * 使用Range记录每个节点的范围，每个节点应该大于Range.low,小于Range.high
     * 由于最左侧的节点没有最小节点可比较，所以初始化为Integer.MIN_VALUE
     * 最右侧的节点没有最大节点可比较，所以初始化为Integer.MAX_VALUE
     *
     *
     *
     *
     * @param root
     * @param range
     * @return
     */
    private boolean validate(TreeNode root, Range range) {
        if ((root.val > range.low) && (root.val < range.high)) {
            long temp = range.high;
            if (root.left != null) {
                range.high = root.val;
                if (!validate(root.left, range)) return false;
            }
            if (root.right != null) {
                range.high = temp;
                range.low = root.val;
                if (!validate(root.right, range)) return false;
            }
            return true;
        } else return false;
    }

}
