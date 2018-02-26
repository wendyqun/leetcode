package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 23/02/2017.
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */
public class PostorderToBT {

    private Map<Integer, Integer> INDEX = new HashMap<>();
    private static int postIndex;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode myFun(int postOrder[],int inorder[]){
        int postLen=postOrder.length;
        int inLen=inorder.length;
        return sub(postOrder,0,postLen-1,inorder,0,inLen-1);
    }

    public TreeNode sub(int postOrder[],int ps,int pe,int inOrder[],int ins,int ine){
        if(ins>ine|| ps>pe)
            return null;
        int rootIndex=pe;
        int rootVal=postOrder[pe];
        for(int i=ins;i<=ine;i++){
            if(inOrder[i]==rootVal){
                rootIndex=i;
            }
        }
        TreeNode root=new TreeNode(rootVal);
        int len=rootIndex-ins;
        //中序-> 左 中 右
        //后序-> 左 右 中
        //如果将“中”拿出来构造了根节点后，都剩下了 左 右
        root.left=sub(postOrder,ps,ps+len-1,inOrder,ins,rootIndex-1);
        root.right=sub(postOrder,ps+len,pe-1,inOrder,rootIndex+1,ine);
        return root;
    }

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int in[] = new int[]{1, 2};
        int post[] = new int[]{1, 2};
        int myIn[]={4,2,5,1,6,3,7};
        int myPost[]={4,5,2,6,7,3,1};
        TreeNode root = new PostorderToBT().buildTree(myIn, myPost);
        new PostorderToBT().preorderPrint(root);
        TreeNode myRoot=new PostorderToBT().myFun(myPost,myIn);
        System.out.println(myRoot);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int count = 0;
        for (int i : inorder)
            INDEX.put(i, count++);
        postIndex = postorder.length - 1;
        return build(0, inorder.length - 1, postorder);
    }

    private void preorderPrint(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorderPrint(root.left);
            preorderPrint(root.right);
        }
    }

    /**
     * 这种解法不太好理解
     * @param s
     * @param e
     * @param postorder
     * @return
     */
    private TreeNode build(int s, int e, int[] postorder) {
        if (postIndex >= 0 && s <= e) {
            int poi = postorder[postIndex];

            int ini = INDEX.get(poi);

            TreeNode node = new TreeNode(poi);
            postIndex--;

            if (s == e)
                return node; //leaf node

            node.right = build(ini + 1, e, postorder);
            node.left = build(s, ini - 1, postorder);

            return node;
        }
        return null;
    }

}
