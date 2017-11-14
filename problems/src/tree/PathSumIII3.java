package tree;

/**
 *
 * 该算法算是暴力破解了
 * pathSum方法通过先序遍历方式，遍历每一个节点，每一个节点当做一个新树的根节点
 * helper则计算新树根节点向下所有路径为目标值的路径
 *
 * helper方法计算路径时使用了一个技巧，废除了list来记录所经历的节点值，使用list的弊端
 * 就是每次一个节点递归计算完毕后需要从list中移除
 *
 *
 *
 * PathSumIII
 */
public class PathSumIII3 {
    int res = 0;
    public int pathSum(PathSumIII3.TreeNode root, int sum) {
        if (root == null) return 0;
        helper(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return res;
         
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
     
    public void helper(PathSumIII3.TreeNode root, int sum) {
        if (root == null) return;
        if (sum - root.val == 0) {
            res++;
            if(res==1){
                System.out.println();
            }
            //剪枝
            //return;
        }
        helper(root.left, sum-root.val);
        helper(root.right, sum-root.val);
    }

    public static void main(String [] args){
        PathSumIII3 solution=new PathSumIII3();
        TreeNode root= new TreeNode(10);
        root.left=new TreeNode(5);
        root.right=new TreeNode(-3);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(2);
        root.left.left.left=new TreeNode(0);
        root.left.left.right=new TreeNode(-2);
        root.left.right.right=new TreeNode(1);
        root.right.right=new TreeNode(11);

        System.out.println(solution.pathSum(root,8));
    }
}