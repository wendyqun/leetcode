package two_pointers;

/**
 * Created by gouthamvidyapradhan on 04/07/2017.
 * Given a sorted array, remove the duplicates in place such that each element appear only once
 * and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2,
 * with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicates {
    public static void main(String[] args) throws Exception {
        int[] nums = {1, 1, 2,2,2,4,5};
        int N = new RemoveDuplicates().myFun(nums);
        for (int i = 0; i < N; i++)
            System.out.print(nums[i] + " ");
    }

    /**
     * 双指针
     * start记录有效的位置
     * j负责向后查找
     *
     * @param nums
     * @return
     */
    public static int myFun(int nums[]){
        if(nums.length==1)
            return 1;
        int start=0;
        for(int j=1;j<=nums.length-1;j++){
            if(nums[j]!=nums[start]){
                nums[++start]=nums[j];
            }
        }
        return start+1;
    }



    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int size = 1;
        for (int j = 0, i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                size++;
                j++;
                nums[j] = nums[i];
            }
        }
        return size;
    }
}
