package com.xt.queue;

import java.util.Scanner;

/**
 * 数组队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~~");
        // 说明设置4，其队列的有效数据最大是3
        CircleArrayQueue queue = new CircleArrayQueue(4);
        char key; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        // 输入一个菜单
        while(loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); // 接收一个字符
            switch (key) {
                case 's': // 显示队列
                    queue.showQueue();
                    break;
                case 'a': // 添加数据
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出队头数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队头数据
                    try {
                        int headQueue = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;

                default:
                        break;
            }
        }
        System.out.println("程序退出~~");
    }
}

/**
 * 使用数组模拟队列-编写一个ArrayQueue类
 */
class CircleArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front;   // front 指向队列的第一个元素，也就是说 arr[front] 就是队列的第一个元素，初始值 0
    private int rear;    // rear 指向队列的最后一个元素的后一个位置，空出一个空间作为约定，初始值为 0
    private int[] arr;   // 该数据用于存放数据，模拟队列

    // 创建队列的构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull () {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isEmpty () {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize; // 让 rear 后移，这里必须考虑取模
    }

    // 获取队列的数据，出队列
    public int getQueue() throws Exception {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new Exception("队列空，不能取数据");
        }
        /**
         * 这里需要分析出 front 是指向队列的第一个元素
         * 1. 先把 front 对应的值保留到一个临时变量
         * 2. 将 front 后移，考虑取模
         * 3. 将临时保存的变量返回
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~");
            return;
        }
        // 从 front 开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列中有效数据的个数
    public int size() {
        return (rear + maxSize -front) % maxSize;
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列空，不能取数据");
        }
        return arr[front];
    }
}
