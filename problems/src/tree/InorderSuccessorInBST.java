package tree;

/**
 * 这个有点麻烦，没想出来
 * Created by gouthamvidyapradhan on 14/05/2017.
 * Given a binary search tree and a node in it,
 * find the in-order successor of that node in the BST.
 * <p>
 * Note: If the given node has no in-order successor in the tree, return null.
 * <p>
 * Solution: The below solution works with worst case time complexity of O(h) where h is the height of the tree.
 * If the current node is <= target_node, recursively iterate the right of the current node.
 * else if the current node is > target_node then mark the current node as the successor and recursively iterate the left of the current node.
 */
public class InorderSuccessorInBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    TreeNode val;
    boolean found=false;

    /**
     * 那就直接来中序遍历好了
     * 如果遍历到target节点，那么就标记下
     * 直到再次中序遍历到下一个节点，就打印出来
     * @param root
     * @param target
     */
    public void myFun(TreeNode root,TreeNode target){
        if(root==null)
            return;
        myFun(root.left,target);
        if(found==true){
            val=root;
            found=false;
        }
        if(root==target){
            found=true;
        }
        myFun(root.right,target);
    }

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(13);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(14);
        root.right.right = new TreeNode(17);
        TreeNode ans = new InorderSuccessorInBST().inorderSuccessor(root, root.right);
        if (ans != null)
            System.out.println(ans.val);
        else System.out.println(ans);
        InorderSuccessorInBST t=new InorderSuccessorInBST();
        System.out.println(t.whileOrder(root, root.right).val);
    }

    /**
     * Find successor
     *
     * @param root root node
     * @param p    target
     * @return successor
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return inOrder(root, p, null);
    }

    /**
     * Inorder traversal
     * 思路，其实是根据中序遍历的规律了
     * 如果当前节点A小于目标节点T，那么需要取A.right 与T比较，如果A.right 还小与T，那么继续取A.right.right 与T比较
     * 即使A.right.right与T相等，那么A.right.right（简称B）的后继节点也应该是B的右子树的最左孩子节点
     * 如
     *     10
     *      \
     *       20
     *        \
     *         30---->目标节点
     *          \
     *           40
     *          / \
     *   后继-->35 45
     *
     *
     * 如果当前节点A大于目标节点T，那么需要取A.left与T比较，同时设置A为A.left的前驱（根据中序遍历规则）
     *
     * 总结：
     * 如果目标节点只有左子树，那么目标节点的父节点就是后继
     * 如果目标节点有右子树，那么目标节点右子树的最左节点就是后继
     * 往右走不更新后继，往左走更新后继,找到目标节点时，需要往右走，因为往左走会更新后继变量。
     * @param curr      current node
     * @param target    target node
     * @param successor successor
     * @return successor node
     */
    private TreeNode inOrder(TreeNode curr, TreeNode target, TreeNode successor) {
        if (curr == null) return successor;
        if (curr.val <= target.val)
            return inOrder(curr.right, target, successor);
        return inOrder(curr.left, target, curr); //make the current node as successor
    }

    private TreeNode whileOrder(TreeNode curr,TreeNode target){
        TreeNode succ=null;
        //把握好何时左转何时右转，主要是相等时左转还是右转
        while(curr!=null){
            if(curr.val<=target.val){
                curr=curr.right;
            }else{
                succ=curr;
                curr=curr.left;
            }
        }
        return succ;
    }
}
