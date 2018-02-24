package tree;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 Given a binary tree, return all root-to-leaf paths.
 For example, given the following binary tree:
    1
  /   \
  2     3
   \
    5
 All root-to-leaf paths are:

 ["1->2->5", "1->3"]

 思路：
    按照先序遍历的方式
    用list存储先序遍历后的每一个节点值，一旦遇到节点为叶子节点，则记录list中的元素，此为路径,记录并返回
    否则，需要递归调用该该节点的子节点。一旦访问完毕左子节点，则从list中删除。同样删除右子节点

    思路要局部在某颗树内看问题
 * @author Hale Li 2017/10/29
 */
public class BinaryTeeePaths {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public List<String> myFun(TreeNode root){
        LinkedList<String> tmpList=new LinkedList<>();
        List<String> resList=new ArrayList<>();
        mySub(root,tmpList,resList);
        return resList;
    }

    /**
     * 该方法思路更清晰一些
     * 采用先序遍历，先遍历根节点，再递归遍历左右子节点，一旦遍历完毕，则从列表中删除该根节点
     * 如果根节点无左右子树，则算是找到一条路径，记录该路径，同时在return之前删除根节点。
     * @param root
     * @param tmpList
     * @param resList
     */
    public void mySub(TreeNode root,LinkedList<String> tmpList,List<String> resList){
        if(root==null)
            return;
        tmpList.add(root.val+"");
        if(root.left==null&&root.right==null){
            resList.add(StringUtils.join(tmpList,","));
            tmpList.removeLast();
            return;
        }

        mySub(root.left,tmpList,resList);
        mySub(root.right,tmpList,resList);
        tmpList.removeLast();
    }


    public void sub(TreeNode root,LinkedList<String> tmpList,List<String> resList){
        if(root==null)
            return;
        tmpList.add(root.val+"");
        if(root.left==null&&root.right==null){
            resList.add(StringUtils.join(tmpList, ","));
            return;
        }
        if(root.left!=null){
            sub(root.left,tmpList,resList);
            tmpList.removeLast();
        }
        if(root.right!=null){
            sub(root.right,tmpList,resList);
            tmpList.removeLast();
        }
    }

    public static void main(String [] args){
        BinaryTeeePaths paths=new BinaryTeeePaths();
        BinaryTeeePaths.TreeNode root=new BinaryTeeePaths.TreeNode(10);
        root.left=new TreeNode(5);
        root.right=new TreeNode(-3);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(2);
        root.right.right=new TreeNode(11);
        root.left.left.left=new TreeNode(3);
        root.left.left.right=new TreeNode(-2);
        root.left.right.right=new TreeNode(1);

        List<String> strings = paths.myFun(root);
        for(String s:strings){
            System.out.println(s);
        }
    }
}
