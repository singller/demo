package com.zjx.demo.leecode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 实例
 * 输入: "()"
 * 输出: true
 *
 * @date:2020/8/4
 **/
public class VaildBracket {

    public Map<Character,Character> map = new HashMap<Character,Character>();

    public VaildBracket(){
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
    }


    public boolean solution1(String str){

        if(str.length()%2 != 0){
            return false;
        }
        String replace = "";
        while (str.contains("{}") || str.contains("[]") || str.contains("()")){
            str = str.replace("{}","");
            str = str.replace("[]","");
            str = str.replace("()","");
        }
        return "".equals(str);
    }

    public boolean solution2(String str){
        Stack<Character> stack = new Stack<>();
        //遍历字符串
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            //判断map中是否包含此元素
            if(map.containsKey(c)){
                //判断栈是否为空
                char topElement = stack.empty() ? '#' : stack.pop();
                //判断map中值是否跟栈中值相等
                if(topElement != map.get(c)){
                    return false;
                }
            }else {
                //否则进行压栈
                stack.push(c);
            }
        }
        return stack.empty();
    }
}
