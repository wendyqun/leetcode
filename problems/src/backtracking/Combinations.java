package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 有难度
 * Created by pradhang on 3/8/2017.
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combinations {
    /**
     * 输出结果
     1 2 3
     1 2 4
     1 2 5
     1 3 4
     1 3 5
     1 4 5
     2 3 4
     2 3 5
     2 4 5
     3 4 5
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //List<List<Integer>> result = new Combinations().combine(5, 3);
        List<List<Integer>> result2 = new Combinations().combine2(5, 3);
        for(List<Integer> list:result2){
            for(int  i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        int[] subArr = new int[k];
        List<List<Integer>> result = new ArrayList<>();
        getNext(0, 0, n, k, subArr, result);
        return result;
    }

    private void getNext(int i, int count,
                         int n, int k, int[] subArr, List<List<Integer>> result) {
        if (k == 0) {
            List<Integer> subList = new ArrayList<>();
            for (int a : subArr)
                subList.add(a);
            result.add(subList);
        } else {
            for (int j = i + 1; j <= n; j++) {
                subArr[count] = j;
                getNext(j, count + 1, n, k - 1, subArr, result);
            }
        }
    }


    /**
     * http://www.jianshu.com/p/f116a6f65c6d
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combine(combs, new ArrayList<Integer>(), 1, n, k);
        return combs;
    }
    public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
        if(k==0) {
            combs.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=start;i<=n;i++) {
            comb.add(i);
            combine(combs, comb, i+1, n, k-1);
            comb.remove(comb.size()-1);
        }
    }
}
