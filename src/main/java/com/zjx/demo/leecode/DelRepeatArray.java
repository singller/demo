package com.zjx.demo.leecode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 **/
public class DelRepeatArray {
    public static void main(String[] args) {
        int [] nums ={0,0,1,1,1,2,2,3,3,4};
        int i = removeDuplicates(nums);
        System.out.println(i);
    }

    /*
    * 双指针法
    * */
    public static int removeDuplicates(int[] nums) {

        if(nums.length == 0 ) return 0;
        //先遍历循环数组
        int i=0;
        for(int j=1;j<nums.length;j++){
            //判断两个指针元素值是否相等
            if(nums[i] != nums[j]){
                //不相等  则把后边元素相前移动
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
