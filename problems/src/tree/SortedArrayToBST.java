package tree;

/**
 * Created by gouthamvidyapradhan on 25/02/2017.
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class SortedArrayToBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：
     * 平衡二叉树的特点是按照中序遍历的方式所得的数组是递增的
     * 所以取出数组中间的值就构造了一个根节点，同样的方式，中间节点左侧的数组，构建出来的节点是左子节点，右侧同理
     * 我们再处理过程中需要构造根节点及左右子树，同时将本次构造完毕的根节点返回给上层
     * @param array
     * @return
     */
    public static TreeNode myFun(int array[]){
        TreeNode root=new TreeNode(-1);
        int length=array.length;
        int mid=length>>1;
        root.val=array[mid];
        root.left=sub(array, 0, mid - 1);
        root.right=sub(array,mid+1,length-1);
        return root;
    }
    public static TreeNode sub(int array[],int start,int end){
        if(start>end)
            return null;
        int mid=(start+end)>>1;
        TreeNode root=new TreeNode(array[mid]);
        root.left=sub(array,start,mid-1);
        root.right=sub(array,mid+1,end);
        return root;
    }

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 3, 4, 5, 6};
        new SortedArrayToBST().sortedArrayToBST(A);
        TreeNode root=myFun(A);
        System.out.println(root.val);

    }




    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;

        TreeNode root = new SortedArrayToBST().build(0, nums.length - 1, nums);
        preorder(root);
        return root;
    }

    private void preorder(TreeNode node) {
        if (node != null) {
            preorder(node.left);
            System.out.println(node.val);
            preorder(node.right);
        }
    }

    private TreeNode build(int s, int e, int[] nums) {
        if (s > e) return null;

        int m = (e - s) / 2;
        int node = nums[s + m];
        TreeNode root = new TreeNode(node);
        if (s == e)
            return root;

        root.left = build(s, s + m - 1, nums);
        root.right = build(s + m + 1, e, nums);

        return root;
    }

}
