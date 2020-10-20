package com.zjx.demo.leecode;

import java.util.HashMap;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date:2020/8/10 标签：滑动窗口
 * 暴力解法时间复杂度较高，会达到 O(n^2)O(n
 * 2
 * )，故而采取滑动窗口的方法降低时间复杂度
 * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
 * 我们定义不重复子串的开始位置为 start，结束位置为 end
 * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
 * 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
 * 时间复杂度：O(n)O(n)
 * <p>
 * 作者：guanpengchn
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-jie-suan-fa-3-wu-zhong-fu-zi-fu-de-zui-chang-z/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 **/
public class LengthOfLongestSubstring {


    public static void main(String[] args) {
        System.out.println(Math.max(9, 4));
        String str = "asdfgaaed";
        System.out.println(solution(str));
    }

    public static int solution(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < n; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c), start);
            }
            ans = Math.max(ans, (end - start + 1));
            map.put(s.charAt(end), end + 1);
        }

        return ans;
    }
}
