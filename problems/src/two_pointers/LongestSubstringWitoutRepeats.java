package two_pointers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gouthamvidyapradhan on 09/03/2017.
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWitoutRepeats {
    Set<Character> set = new HashSet<>();

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(new LongestSubstringWitoutRepeats()
                .lengthOfLongestSubstring("asdfsdfvesdfsdfasdfdjdjjdjjdjjjjjajsdjjdjdjjd"));
        System.out.println('a'-0);
        System.out.println('z'-0);
    }

    /**
     * 思路：
     * 使用map记录数组中每个char的下标，若存在该char则覆盖
     * 初始start为0，end为当前遍历的char的位置，每次都要计算end-start的距离，使用max变量存储最大的距离
     * 如果map中存在当前char，则取出map中该char的位置来更新start
     * @param s
     * @return
     */
    private int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, max = Integer.MIN_VALUE;
        for (int j = 0, l = s.length(); j < l; j++) {
            if (map.keySet().contains(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            map.put(s.charAt(j), j);
            max = Math.max(max, (j - i) + 1);
            System.out.println(String.format("Math.max(%s, %s)",max,j-i+1));
        }
        return max;
    }
}
