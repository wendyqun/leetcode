package binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 23/05/2017.
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 * <p>
 * Solution: Works in worst case time complexity of O(log min(m, n))
 * <p>
 * The basic idea is that if you are given two arrays A and B and know
 * the length of each, you can check whether an element A[i] is the median in constant
 * time. Suppose that the median is A[i]. Since the array is sorted, it is greater than
 * exactly i − 1 values in array A. Then if it is the median, it is also greater than exactly
 * j = [n / 2] − (i − 1) elements in B. It requires constant time to check if B[j]
 * A[i] <= B[j + 1]. If A[i] is not the median, then depending on whether A[i] is greater
 * or less than B[j] and B[j + 1], you know that A[i] is either greater than or less than
 * the median. Thus you can binary search for A[i] in O(log N) worst-case time
 */
public class MedianOfTwoSortedArrays {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 5, 8};
        int[] B = {1, 2, 3, 4, 5, 6, 7, 23, 23, 23, 33, 44, 45, 45, 56, 77, 5555};
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(A, B));
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays2(A, B));
    }

    /**
     * Find median
     *
     * @param nums1 array one
     * @param nums2 array two
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1); //ensure always nums1 is the shortest array
        int T = nums1.length + nums2.length, low = -1, high = -1;
        int median = (T - 1) / 2;
        boolean isOdd = false;
        if ((T % 2) != 0)
            isOdd = true;

        int s = 0, e = nums1.length - 1;
        while (s <= e) {
            int m = s + (e - s) / 2;
            if ((median - m - 1) < 0 || nums1[m] >= nums2[median - m - 1]) {
                e = m - 1;
                low = m;
                high = median - m;
            } else s = m + 1;
        }

        if (low == -1) {
            if (isOdd) return nums2[median - nums1.length];
            else return (double) (nums2[median - nums1.length] + nums2[median - nums1.length + 1]) / 2.0D;
        } else {
            if (isOdd) return nums1[low] < nums2[high] ? nums1[low] : nums2[high];
            else {
                //Always sorts maximum of 4 elements hence works in O(1)
                List<Integer> list = new ArrayList<>();
                list.add(nums1[low]);
                if (low + 1 < nums1.length)
                    list.add(nums1[low + 1]);
                list.add(nums2[high]);
                if (high + 1 < nums2.length)
                    list.add(nums2[high + 1]);
                Collections.sort(list);
                return (double) (list.get(0) + list.get(1)) / 2.0;
            }
        }
    }

    /**
     * 问题可以转化为求第K个最小元素
     * 思路：
     * 已经排序好的数组A和数组B，A长度小于等于B长度
     * 从A中定位到第K/2=m个元素,记为E1
     * 从B中定位到第K-m=n个元素,记为E2
     * 例如，K=9，则m=4，n=5，对应的E1=A[3],E2=B[4],这里假设没有数组越界
     * E1 compare E2,结果为三种情况
     * 1)E1<E2,那么可以肯定的是E1及之前的元素肯定在K个最小元素集合中
     * 2)E1>E2,那么可以肯定的是E2及之前的元素肯定在K个最小元素集合中
     * 3)E1=E2,那么E1或者E2就是要找的值
     *
     * 这里解释下为什么可以这样判断
     * 假如E1<E2,但是E1不在K个最小元素当中，那么因为E2>E1,所以E2也不在K个元素集合中
     * 那么此时A中的m-1个元素加B中的n-1个元素=m+n-2 =K-2,不足K个,所以E1必定需要在K个元素当中
     *
     * 以假设1为条件：
     * 之后,我们就可以快速排除掉A中的前m个元素,然后从A中剩余的元素和B中找第K-m 最小元素,此过程是上述过过程的一个轮回
     * 因此可以递归实现
     *
     * 剩下的就是考虑极端情况:
     * 如果A集合为空,则返回B集合中k-1个位置的数值
     * 如果K=1,则返回A[0]与B[0]中最小的
     *
     *
     * @param arr1
     * @param arr2
     * @param start1
     * @param start2
     * @param len1
     * @param len2
     * @param k
     * @return
     */
    private double findKth(int[] arr1, int[] arr2, int start1, int start2, int len1, int len2, int k){
        // 保证arr1是较短的数组
        if(len1>len2){
            return findKth(arr2,arr1,start2,start1,len2,len1,k);
        }
        if(len1==0){
            return arr2[start2 + k - 1];
        }
        if(k==1){
            return Math.min(arr1[start1],arr2[start2]);
        }
        int p1 = Math.min(k/2,len1) ;
        int p2 = k - p1;
        if(arr1[start1 + p1-1]<arr2[start2 + p2-1]){
            return findKth(arr1,arr2,start1 + p1,start2,len1-p1,len2,k-p1);
        } else if(arr1[start1 + p1-1]>arr2[start2 + p2-1]){
            return findKth(arr1,arr2,start1,start2 + p2,len1,len2-p2,k-p2);
        } else {
            return arr1[start1 + p1-1];
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        //确保长的数组在前
        if (m < n) return findMedianSortedArrays2(nums2, nums1);
        if (n == 0) return (nums1[(m - 1) / 2] + nums1[m / 2]) / 2.0;
        int left = 0, right = 2 * n;
        while (left <= right) {
            int mid2 = (left + right) / 2;
            int mid1 = m + n - mid2;
            double L1 = mid1 == 0 ? Double.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = mid2 == 0 ? Double.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = mid1 == m * 2 ? Double.MAX_VALUE : nums1[mid1 / 2];
            double R2 = mid2 == n * 2 ? Double.MAX_VALUE : nums2[mid2 / 2];
            if (L1 > R2) left = mid2 + 1;
            else if (L2 > R1) right = mid2 - 1;
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
        }
        return -1;
    }
}
