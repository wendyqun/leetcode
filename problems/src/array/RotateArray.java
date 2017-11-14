package array;

/**
 * Created by gouthamvidyapradhan on 01/08/2017.
 * Rotate an array of n elements to the right by k steps.
 * <p>
 * For example, with n = 7 and k = 3,
 * the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * <p>
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * <p>
 * Hint:
 * Could you do it in-place with O(1) extra space?
 * Related problem: Reverse Words in a String II
 */
public class RotateArray {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 3, 4, 5, 6};
        new RotateArray().rotate(A, 2);
        for (int i : A)
            System.out.print(i + " ");
    }

    /**
     * 旋转三次
     * 第一次整体旋转
     * 第二次第三次局部旋转
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 具体旋转方式
     * 头尾交换
     * @param nums
     * @param s
     * @param e
     */
    private void reverse(int[] nums, int s, int e) {
        for (int i = s, j = e; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
