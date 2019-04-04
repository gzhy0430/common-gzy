package com.indi.argorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 三数之和:给定一个n个元素的数组，是否存在a，b，c三个元素，使得a+b+c=target，找出所有符合这个条件的三元组
 * 解题思路·每次从数组中选出一个数K，从剩下的数中求目标等于target-K的2Sum问题。
 */
public class ThreeSumX {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 1, 6)));
//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println(threesum(arr, 0));
    }

    public static List<List<Integer>> threesum(int[] nums, int target){
        List<List<Integer>> result = new LinkedList<>();
        if(nums != null && nums.length > 2){
            Arrays.sort(nums);
            for(int i = 0; i < nums.length - 2;){
                int j = i + 1;
                int k = nums.length - 1;
                while(j < k){
                    if(nums[j] + nums[k] == -nums[i]){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);list.add(nums[j]);list.add(nums[k]);
                        result.add(list);
                        k--; j++;
//                        从左向右找第一个与之前处理的数不同的数的下标
                        while(j < k && nums[j] == nums[j - 1]){
                            j++;
                        }
                        while(j < k && nums[k] == nums[k + 1]){
                            k--;
                        }
                    }else if(nums[j] + nums[k] > -nums[i]){//和>0,应该下标左移变小
                        k--;
                        while(j < k && nums[k] == nums[k + 1]){
                            k--;
                        }
                    }else{
                        j++;
                        while(j < k && nums[j] == nums[j - 1]){
                            j++;
                        }
                    }
                }
                i++;//指向下一个要处理的第一个值
//                从左向右找第一个与之前处理的数不同的数的下标
                while(i < nums.length - 2 && nums[i] == nums[i - 1]){
                    i++;
                }
            }
        }
        return result;
    }
}
