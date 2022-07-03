package leetcode.arrays;

import java.util.HashMap;

public class No001 {
    public static void main(String[] args) {
        Test001 test001 = new Test001();
        int[] ints = {2, 7, 11, 15};
        int target = 18;
        int[] result = test001.countSum(ints, target);
        //输出返回的数组
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + "  ");
        }
    }
}
class Test001{
    public int[] countSum(int[] nums, int target){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(hm.containsKey(target - nums[i])){//containsKey用于获取是否存在某个键
                return new int[]{hm.get(target - nums[i]),i};//get用于获取这个键对应的value
                //这里写的这个顺序还会影响到输出的结果
            }
            hm.put(nums[i],i);//把数组和他对应的编号以key-value存进来
        }
        return new int[0];//这就是一个空的数组
    }
}