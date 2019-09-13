package com.xt.linkedlist;

/**
 * 带头节点的单链表的增删改查
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建链表
        SingleLinkedList list = new SingleLinkedList();

        // 创建节点
        /*// 不考虑编号顺序
        HeroNode node1 = new HeroNode(1, "孙悟空", "老孙");
        HeroNode node2 = new HeroNode(2, "猪八戒", "老猪");
        HeroNode node3 = new HeroNode(3, "沙和尚", "老沙");
        HeroNode node4 = new HeroNode(4, "三太子", "小白龙");

        // 加入节点
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        */

        HeroNode node1 = new HeroNode(1, "孙悟空", "老孙");
        HeroNode node4 = new HeroNode(4, "三太子", "小白龙");
        HeroNode node2 = new HeroNode(2, "猪八戒", "老猪");
        HeroNode node3 = new HeroNode(3, "沙和尚", "老沙");

        list.addByOrder(node1);
        list.addByOrder(node4);
        list.addByOrder(node2);
        list.addByOrder(node3);

        // 显示 链表
        System.out.println("原始链表：");
        list.list();

        // 更新
        HeroNode newNode3 = new HeroNode(3, "沙师弟", "流沙河的老沙");
        list.update(newNode3);
        System.out.println("更新后的链表：");
        list.list();

        // 删除
        list.del(1);
        list.del(4);
        System.out.println("删除后的链表：");
        list.list();

    }
}

/**
 * 定义 SingleLinkedList 管理 英雄节点
 */
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 当不考虑编号顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的 next 指向 新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {

        // 因为 head 节点不能动，因此需要一个 辅助遍历 tmp
        HeroNode tmp = head;

        // 遍历链表，找到最后
        while (true) {
            if (tmp.next == null) {
                break;
            }

            // 如果没有找到最后，将 tmp 后移
            tmp = tmp.next;
        }
        // 当退出 while 循环时，tmp就指向了链表的最后
        // 将这个节点的 next 指向 新的节点
        tmp.next = heroNode;
    }

    /**
     * 第二种添加英雄时，根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示
     * @param node
     */
    public void addByOrder(HeroNode node) {
        /**
         * 因为 head 节点不能动，因此通过一个 辅助指针（变量）来帮助找到添加的位置
         * 因为单链表，找的 tmp 是位于 添加位置的前一个节点。否则插入不了
         */
        HeroNode tmp = head;
        boolean flag = false; // 标志添加的编号是否存在，默认为 false
        while (true) {
            if (tmp.next == null) { // 说明 tmp 已经在链表的最后
                break;
            }
            if (tmp.next.no > node.no) { // 位置找到，就在 tmp 的后面插入
                break;
            } else if (tmp.next.no == node.no) { // 说明希望添加的 节点 的编号已经存在
                flag = true; // 说明编号存在
                break;
            }
            tmp = tmp.next; // 后移，遍历当前链表
        }
        // 判断 flag 的值
        if (flag) { // 不能添加，说明编号已经存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", node.no );
        } else {
            // 插入到链表中，tmp 的后面
            node.next = tmp.next;
            tmp.next = node;
        }

    }


    /**
     * 修改节点的信息，根据 no 编号来修改，即 no 编号不能改
     * @param node
     */
    public void update(HeroNode node) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        // 找到需要修改的节点，根据 no 编号 定义一个辅助变量
        HeroNode tmp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (tmp == null) {
                break; // 已经遍历完链表
            } else if (tmp.no == node.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.name = node.name;
            tmp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", node.no);
        }
    }

    /**
     * 删除节点
     * 1. head 不能动，需要一个 tmp 辅助节点找到待删除节点的前一个节点
     * 2. 说明在比较时，是 tmp.next.no 和 需要删除的节点的 no 比较
     * @param no
     */
    public void del (int no) {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据 no 编号
        // 定义一个辅助变量
        HeroNode tmp = head;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (tmp.next == null) { // 已经到链表的最后
                break;
            }
            if (tmp.next.no == no) { // 找到待删除链表的前一个节点 tmp
                flag = true;
                break;
            }
            tmp = tmp.next; // tmp 后移，遍历
        }
        // 判断 flag
        if (flag) { // 找到可以删除
            tmp.next = tmp.next.next;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能删除\n", no);
        }
    }

    /**
     * 显示链表（遍历）
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 因为 头节点 不能动，因此需要一个 辅助变量 来遍历
        HeroNode tmp = head.next;
        while (true) {
            // 判断是否到链表的最后
            if (tmp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(tmp.toString());
            // 将 tmp 后移
            tmp = tmp.next;
        }
    }
}


/**
 * 定义 HeroNode，每个 HeroNode 对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}