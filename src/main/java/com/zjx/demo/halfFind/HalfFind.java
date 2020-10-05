package com.zjx.demo.halfFind;

/*
  二分查找法
 */
public class HalfFind {

    public static int halfSearch(int[] arrays, int a) {
        int lo = 0, hi = arrays.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;//中间位置
            if (arrays[mid] == a) {
                return mid;
            } else if (a > arrays[mid]) {//在右边
                lo = mid;
            } else {//在左边
                hi = mid;
            }
        }
        return -1;
    }
}
