package com.zjx.demo.halfFind;

/**
  二分查找法
 * @author zjx
 */
public class HalfFind {

    public static int halfSearch(int[] arrays, int a) {
        int lo = 0, hi = arrays.length - 1;
        int mid;
        while (lo <= hi) {
            //中间位置
            mid = (lo + hi) / 2;
            if (arrays[mid] == a) {
                return mid;
            //在右边
            } else if (a > arrays[mid]) {
                lo = mid;
            } else {//在左边
                hi = mid;
            }
        }
        return -1;
    }
}
