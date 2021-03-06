package tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gouthamvidyapradhan on 27/03/2017.
 * Given the root of a tree, you are asked to find the most frequent subtree sum.
 * The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself).
 * So what is the most frequent subtree sum value? If there is a tie,
 * return all the values with the highest frequency in any order.
 * <p>
 * Examples 1
 * Input:
 * <p>
 * 5
 * /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2
 * Input:
 * <p>
 * 5
 * /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Map<Integer, List<Integer>> fList = new HashMap<>();
    private Map<Integer, Integer> fCount = new HashMap<>();

    public  void myFun(TreeNode root){
        if(root==null)
            return ;
        sub(root);
        List<Integer> list=new ArrayList<>();
        int max=0;
        for(Map.Entry<Integer,List<Integer>> entry: fList.entrySet()){
            if(entry.getKey()>max){
                list=entry.getValue();
                max=entry.getKey();
            }
        }
        for(Integer i:list){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 这里没有理解好节点为空时，返回值为0是否可以，所以徒增了很多判空逻辑，其实是没有必要的
     * @param root
     * @return
     */
    public  int sub(TreeNode root) {
        if (root.left == null && root.right == null) {
            compute(root.val);
            return root.val;
        }
        int leftSum = 0, rightSum = 0;
        if (root.left != null) {
            leftSum = sub(root.left);

        }
        if (root.right != null) {
            rightSum = sub(root.right);
        }
        if (root.left == null) {
            compute(root.val+rightSum);
            return root.val + rightSum;
        } else if(root.right==null) {
            compute(root.val+leftSum);
            return root.val + leftSum;
        }else{
            compute(root.val+rightSum+leftSum);
            return root.val + rightSum+leftSum;
        }
    }

    public void compute(int value){
        if(fCount.containsKey(value)){
            int count=fCount.get(value);
            fCount.put(value,count+1);
            List<Integer> count1List=fList.get(count+1);
            if(count1List==null){
                count1List=new ArrayList<>();
                fList.put(count+1,count1List);
            }
            if(!count1List.contains(value)){
                count1List.add(value);
            }
        }else{
            fCount.put(value,1);
            List<Integer> list=fList.get(1);
            if(list==null){
                list=new ArrayList<>();
                fList.put(1,list);
            }
            list.add(value);
        }
    }





    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MostFrequentSubtreeSum mfs = new MostFrequentSubtreeSum();
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.right = new TreeNode(-3);
        mfs.myFun(node);
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        int[] resArr = new int[0];
        if (root == null) return resArr;
        postOrder(root);
        for (Map.Entry<Integer, Integer> entry : fCount.entrySet()) {
            int frequency = entry.getValue();
            List<Integer> list = fList.get(frequency);
            if (list == null)
                list = new ArrayList<>();
            list.add(entry.getKey());
            fList.put(frequency, list);
        }
        int max = Integer.MIN_VALUE;
        List<Integer> result;
        for (int key : fList.keySet())
            max = Math.max(max, key);
        result = fList.get(max);
        resArr = new int[result.size()];
        for (int i = 0, l = resArr.length; i < l; i++)
            resArr[i] = result.get(i);
        return resArr;
    }

    /**
     * 还是这个方法标准
     * 后序遍历，计算没颗子树节点之和
     * 对于叶子节点，左右子树值为0，加上此根节点（叶子节点），和正好为叶子节点值本身
     * @param root
     * @return
     */
    private int postOrder(TreeNode root) {
        if (root == null) return 0;
        int sum = postOrder(root.left) + postOrder(root.right) + root.val;
        Integer fSum = fCount.get(sum);
        if (fSum == null)
            fCount.put(sum, 1);
        else fCount.put(sum, fSum + 1);
        return sum;
    }

}
