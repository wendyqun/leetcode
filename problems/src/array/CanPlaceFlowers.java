package array;

/**
 * Created by gouthamvidyapradhan on 10/06/2017.
 * Accepted
 * <p>
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
 * <p>
 * Given a flowerbed (represented as an array containing 0 and 1,
 * where 0 means empty and 1 means not empty),
 * and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 * <p>
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 * Note:
 * The input array won't violate no-adjacent-flowers rule.
 * The input array size is in the range of [1, 20000].
 * n is a non-negative integer which won't exceed the input array size.
 */
public class CanPlaceFlowers {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] n = {0, 0, 0, 0, 1};
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(n, 2));
        System.out.println(new CanPlaceFlowers().myFun(n,2));
    }

    /**
     * 思路简单，只要目标坑位前后两个坑位都没有种花，那么此坑位就可以种花
     * 该函数改变了原来array的状态，不过可以开始复制一个
     * @param array
     * @param n
     * @return
     */
    public boolean myFun(int array[],int n){
        int size=array.length;
        int able=0;
        if(n>=size)
            return false;
        for(int i=0;i<size;i++){
            if(i==0){
                if(array[i]==0&&array[i+1]==0){
                    able++;
                    array[i]=1;
                }
            }else if(i==size-1){
                if(array[i-1]==0&&array[i]==0){
                    able++;
                    array[i]=1;
                }
            }else{
                if(array[i-1]==0&&array[i]==0&&array[i+1]==0){
                    array[i]=1;
                    able++;
                }
            }
        }
        if(n>able)
            return false;
        return true;
    }


    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        int[] T = new int[flowerbed.length + 4];
        for (int i = 0, j = 2; i < flowerbed.length; i++)
            T[j++] = flowerbed[i];
        T[0] = 1;
        T[T.length - 1] = 1;
        int total = 0, count = 0;
        for (int i = 1; i < T.length; i++) {
            if (T[i] == 0)
                count++;
            else {
                if ((count % 2) == 0)
                    total += ((count / 2) - 1);
                else
                    total += (count / 2);
                count = 0; //reset
            }
        }
        return (total >= n);
    }
}
