package com.xt.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 创建一个 ArrayStack 对象 --》表示栈
        ArrayStack stack = new ArrayStack(4);
        String key ;
        boolean loop = true; // 控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈（入栈）");
            System.out.println("pop：表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择");

            key = scanner.next();

            switch(key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();  // 关闭流
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入要入栈的整数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

/**
 * 定义一个 ArrayStack 表示栈
 */
class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组 模拟栈，数据就放在该数组中
    private int top = -1; // top 表示栈顶，初始值为 -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 入栈 - push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法添加数据~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈 - pop 将栈顶的数据返回
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，没有数据~~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈的情况【遍历栈】，遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("空栈，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}