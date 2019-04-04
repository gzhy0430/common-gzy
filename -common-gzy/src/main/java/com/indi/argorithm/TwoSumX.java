package com.indi.argorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/15.
 * 给定一个整数数组nums和一个目标值target，在该数组中找出和为目标值的两个整数，并返回数组下标
 */
public class TwoSumX {
    public static void main(String[] args) {
//        int[] arr = {2, 7, 11, 15};
//        int[] arr = {3, 3};
//        System.out.println(Arrays.toString(method3(arr, 9)));

        int[] arr = {0, 1, 2, -1};
        System.out.println(Arrays.toString(method3(arr, 1)));
    }

    /*
     * 使用哈希表2,时间复杂度O(n)，空间复杂度O(n)
     */
    static int[] method3(int[] nums, int target){
        int[] result = {};
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            m.put(nums[i], i);
        }
        for(int i = 0; i < nums.length; i++){
            int c = target - nums[i];
            if(m.containsKey(c) && m.get(c) != i){
                result = new int[]{i, m.get(c)};
            }
        }
        return result;
    }

    /*
    * 使用哈希表1，时间复杂度O(n), 空间复杂度O(n)
    */
    static int[] method2(int[] nums, int target){
        int[] result = {};
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if (m.containsKey(target - nums[i])){
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
            /*if(!m.containsKey(nums[i])){
                m.put(nums[i], i);
            }
            if(m.containsKey(target - nums[i]) && i != m.get(target - nums[i])){
                result = new int[]{(int) m.get(target - nums[i]), i};
            }*/
        }
        return result;
    }

    /*
    * 暴力法-时间复杂度，O(n^2)，空间复杂度，O(1)
    */
    static int[] method1(int[] nums, int target){
        int[] result = {};
//        lable: outer:
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result =  new int[]{i ,j};
                    return result;
//                    break outer;
                }
            }
        }
        return result;
//        System.out.println(Arrays.toString(result));
    }

}
