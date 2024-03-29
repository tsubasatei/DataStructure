package com.xt.linkedlist;

import java.util.Stack;

/**
 * 演示栈的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.add("naruto");
        stack.add("sasuke");
        stack.add("sakura");

        // 出栈: sakura -> sasuke -> naruto
        while (stack.size() > 0) {
            // pop() 就是将栈顶的数据取出
            System.out.println(stack.pop());
        }

    }
}
