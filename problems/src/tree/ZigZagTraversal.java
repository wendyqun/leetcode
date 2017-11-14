package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pradhang on 7/11/2017.
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *  /    \
 * 15    7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class ZigZagTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     *
     *      1
     *   2      3
     * 4   5      6
     7   8          9
   10 11              12

      从队列中取出
      然后将子节点添加到临时队列
      用临时队列替换队列

      第一步
            获取1
            入队2->3

      第二步 获取3 2
            入队6->5->4

      第三部 获取4 5 6
            入队7->8->9

      第四部 获取9 8 7
            入队 12 11 10

     我们发现每次读取的方向和下一次入队的方向是一致的
     那么，如果这次从A 到 B方向读取了，说明队列里也是从A 到B 方向存储的，
     那么下次读取的时候从队列尾部读取就好了，读取后就是从B到A方向。
     *
     * @param node
     */
    public static void myFun(TreeNode node){
        if(node==null)
            return;
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(node);
        boolean left2right=true;
        while(!list.isEmpty()){
            LinkedList<TreeNode> tempList=new LinkedList<>();
            while(!list.isEmpty()){
                TreeNode one=list.removeLast();
                System.out.print(one.val + " ");
                if(left2right){
                    if(one.left!=null)
                        tempList.add(one.left);
                    if(one.right!=null)
                        tempList.add(one.right);
                }else{
                    if(one.right!=null)
                        tempList.add(one.right);
                    if(one.left!=null)
                        tempList.add(one.left);
                }
            }
            left2right=!left2right;
            System.out.println();
            list=tempList;
        }
    }

    public static void main(String[] args) throws Exception {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.right=new TreeNode(6);
        root.left.left.left=new TreeNode(7);
        root.left.left.right=new TreeNode(8);
        root.right.right.right=new TreeNode(9);
        myFun(root);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        dfs(root, 0, result);
        return result;
    }

    @SuppressWarnings("unchecked")
    private void dfs(TreeNode root, int level, List<List<Integer>> result) {
        if (root != null) {
            LinkedList<Integer> subList;
            if (level >= result.size()) {
                subList = new LinkedList<>();
                result.add(subList);
            } else subList = (LinkedList) result.get(level);
            if (level % 2 == 0)
                subList.addFirst(root.val); //add to right
            else subList.add(root.val); //add to left
            dfs(root.right, level + 1, result);
            dfs(root.left, level + 1, result);
        }
    }
}
