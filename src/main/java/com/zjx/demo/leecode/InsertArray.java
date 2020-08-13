package com.zjx.demo.leecode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 **/
public class InsertArray {

    public static void main(String[] args) {
        int []ints ={1,3,5,6};
        int ta = 2;
        int i = searchInsert(ints, ta);
    }

    //强遍历
    public static int searchInsert(int[] nums, int target) {
        int index =0;

        while(index<nums.length){
            if(nums[nums.length-1]<target){
                return nums.length;
            }
            if(nums[index] == target){
                return index;
            }
            if(nums[index]<target){
                index++;
            }else {
                return index;
            }
        }
        return index;
    }

    //二分查找法
    public static int searchInsert1(int[] nums, int target) {
        int left =0,right = nums.length-1,ans =nums.length;
        while (left<=right){
            int mid = ((right - left)>>1)+left;
            if(target<=nums[mid]){
                ans = mid;
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        return ans;
    }
}
