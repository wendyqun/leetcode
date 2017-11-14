package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 25/02/2017.
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * 该题的关键在于快速在preOrder中找到根节点，myFun的解法效率低，容易超时
 * 最后的buildTree2 方法思路清晰
 *
 */
public class PreorderToBT {
    public  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 自己的实现，寻找根节点时绕弯路了，没有利用好先序遍历的特点
     * @param preOrder
     * @param inOrder
     * @return
     */
    public TreeNode myFun(int preOrder[],int inOrder[]){
        TreeNode root=new TreeNode(preOrder[0]);
        int rootIndex=-1;
        for(int i=0;i<inOrder.length;i++){
            if(inOrder[i]==root.val){
                rootIndex=i;
                break;
            }
        }
        root.left=sub(preOrder,inOrder,0,rootIndex-1);
        root.right=sub(preOrder,inOrder,rootIndex+1,inOrder.length-1);
        return root;
    }

    public TreeNode sub(int preOrder[],int inOrder[],int start,int end){
        if(start>end){
            return null;
        }
        int rootVal=-1;
        int rootIndex=-1;
        boolean found=false;
        for(int i=0;i<preOrder.length;i++){
            if(!found){
                for(int j=start;j<=end;j++){
                    if(preOrder[i]==inOrder[j]){
                        rootVal=preOrder[i];
                        rootIndex=j;
                        found=true;
                        break;
                    }
                }
            }
        }
        TreeNode root=new TreeNode(rootVal);
        root.left=sub(preOrder,inOrder,start,rootIndex-1);
        root.right=sub(preOrder,inOrder,rootIndex+1,end);
        return root;
    }

    Map<Integer, Integer> MAP = new HashMap<>();
    private int index = 0, totalLen = 0;

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] perorder = {7, -10, -4, 3, -1, 2, -8, 11};
        int[] inorder = {-4, -10, 3, -1, 7, 11, -8, 2};
        int[] myper={1,2,4,7,8,5,3,6};
        int[] myin={7,4,8,2,5,1,6,3};
        new PreorderToBT().buildTree(perorder, inorder);
        TreeNode root=new PreorderToBT().myFun(myper,myin);
        System.out.println(root.val);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0, l = inorder.length; i < l; i++)
            MAP.put(inorder[i], i);
        totalLen = preorder.length;
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int s, int e) {
        if (s > e || index >= totalLen) return null;

        int n = preorder[index++];
        int pos = MAP.get(n);

        TreeNode node = new TreeNode(n);
        if (s == e) return node;

        node.left = build(preorder, s, pos - 1);
        node.right = build(preorder, pos + 1, e);
        return node;
    }

    /**
     * 下面是比较容易理解的算法
     * 来自：http://www.cnblogs.com/springfor/p/3884034.html
       1
      / \
     2   3
    / \ / \
   4  5 6  7

     对于上图的树来说，
     index: 0 1 2 3 4 5 6
     先序遍历为: 1 2 4 5 3 6 7
     中序遍历为: 4 2 5 1 6 3 7
     为了清晰表示，我给节点上了颜色，红色是根节点，蓝色为左子树，绿色为右子树。归纳总结
     可以发现的规律是：
     1. 先序遍历的从左数第一个为整棵树的根节点。
     2. 中序遍历中根节点是左子树右子树的分割点。

     再看这个树的左子树：
     先序遍历为: 2 4 5
     中序遍历为: 4 2 5
     依然可以套用上面发现的规律。

     右子树：
     先序遍历为: 3 6 7
     中序遍历为: 6 3 7
     也是可以套用上面的规律的。

     所以这道题可以用递归的方法解决。
     具体解决方法是：
     通过先序遍历找到第一个点作为根节点，在中序遍历中找到根节点并记录index。
     因为中序遍历中根节点左边为左子树，所以可以记录左子树的长度并在先序遍历中依据这个长度找到左子树的区间，用同样方法可以找到右子树的区间。
     递归的建立好左子树和右子树就好。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int preLength = preorder.length;
        int inLength = inorder.length;
        return buildTree(preorder, 0, preLength-1, inorder, 0, inLength-1);
    }

    public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd){
        if(inStart > inEnd || preStart > preEnd)
            return null;

        int rootVal = pre[preStart];
        int rootIndex = 0;
        for(int i = inStart; i <= inEnd; i++){
            if(in[i] == rootVal){
                rootIndex = i;
                break;
            }
        }

        int len = rootIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(pre, preStart+1, preStart+len, in, inStart, rootIndex-1);
        root.right = buildTree(pre, preStart+len+1, preEnd, in, rootIndex+1, inEnd);

        return root;
    }
}
