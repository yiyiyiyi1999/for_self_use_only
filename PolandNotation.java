package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
    输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果

 */

public class PolandNotation {
    public static void main(String[] args) {
        String s = "3 4 + 5 × 6 -";
        List<String> l = putInto(s);
        System.out.println(cal(l));
    }
    //把字符串分开，按顺序放在一个List里
    public static List<String> putInto(String s){
        String[] split = s.split(" ");
        List<String> strings = new ArrayList<String>();
        for(String ss:split ){
            strings.add(ss);
        }
        return strings;
    }
    //用来计算的方法
    public static int cal(List<String> l){
        //先创建好一个栈，在这个方法里直接使用，这个方法最后直接就返回计算结果！
        Stack<String> stack = new Stack<String>();
        for(String ss: l){
            if(ss.matches("\\d+")){
                //如果是整数，就入栈
                stack.push(ss);
            }else {
                //如果不是整数，弹出两个数，然后判断符号做相应计算
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                Integer temp;
                if(ss.equals("+")){
                    temp = a+b;
                    stack.push(temp.toString());
                } else if (ss.equals("-")) {
                    temp = b - a;
                    stack.push(temp.toString());
                }else if(ss.equals("×")){
                    temp = a*b;
                    stack.push(temp.toString());
                } else if (ss .equals("/")) {
                    temp = b/a;
                    stack.push(temp.toString());
                }

            }

        }
        return Integer.parseInt(stack.pop());
    }
}
