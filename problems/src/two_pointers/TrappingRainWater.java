package two_pointers;

/**
 * Created by gouthamvidyapradhan on 08/03/2017.
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <p/>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWater().trap(height));
        System.out.println(new TrappingRainWater().trap2(height));
    }

    /*
    思路：考虑到所盛的水取决于容器两端中小的那一端。因此用两个指针，分别指向头和尾，依次向中间移动。
    1.如果左边小，则左边向右移动一格，这个时候需要判断向右移动一格后
    ①如果高度大于原来的就表示盛不下水
    ②如果小于原来的则表示有凹下去的部分，这个时候计算高度差就代表能盛多少水。(右边比左边高，可以保证右边不溢出)
    2.如果右边小，则右边向左移动一格，这个时候同1一样判断。
    */
    public int trap2(int[] height) {
        if (height.length <= 2) return 0;
        int ret = 0;
        int l = 0;
        int r = height.length - 1;
        int left = height[0];
        int right = height[r];
        while (l < r) {
            if (left <= right) {
                l++;
                if (height[l] >= left) {
                    left = height[l];
                } else ret += (left - height[l]);
            } else {
                r--;
                if (height[r] >= right) {
                    right = height[r];
                } else ret += (right - height[r]);
            }
        }
        return ret;
    }


    private int trap(int[] height) {
        if (height.length == 0) return 0;

        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int max = 0;
        left[0] = 0;
        right[height.length - 1] = 0;

        int total = 0;

        for (int i = 1, l = height.length; i < l; i++) {
            left[i] = Math.max(max, height[i - 1]);
            max = left[i];
        }
        max = 0;
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(max, height[i + 1]);
            max = right[i];
        }
        for (int i = 0, l = height.length; i < l; i++) {
            int min = Math.min(left[i], right[i]);
            if (min > height[i]) {
                total += (min - height[i]);
            }
        }
        return total;
    }
}
