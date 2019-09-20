package com.xt.linkedlist;

/**
 * 双向链表的增删改查操作
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 node1 = new HeroNode2(1, "孙悟空", "老孙");
        HeroNode2 node2 = new HeroNode2(2, "猪八戒", "老猪");
        HeroNode2 node3 = new HeroNode2(3, "沙和尚", "老沙");
        HeroNode2 node4 = new HeroNode2(4, "三太子", "小白龙");

        // 添加
//        doubleLinkedList.add(node1);
//        doubleLinkedList.add(node2);
//        doubleLinkedList.add(node3);
//        doubleLinkedList.add(node4);

        // 顺序添加
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node3);

        // 更新
        HeroNode2 newNode3 = new HeroNode2(3, "沙师弟", "流沙河的老沙");
        doubleLinkedList.update(newNode3);

        // 删除
        doubleLinkedList.delete(2);
        // 列表
        doubleLinkedList.list();
    }
}

/**
 * 双向链表类
 */
class DoubleLinkedList {
    // 初始化头节点，头节点不要动，不不能放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", ""); // 头节点

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    // 添加
    public void add(HeroNode2 node) {
        HeroNode2 tmp = head;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
        node.pre = tmp;
    }

    // 按编号顺序添加
    public void addByOrder(HeroNode2 node) {
        HeroNode2 tmp = head;
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            if (tmp.next.no > node.no) {
                break;
            }
            if (tmp.next.no == node.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            System.out.printf("编号 %d 的节点已经存在，无法加入\n", node.no);
        } else {
            if (tmp.next != null) {
                node.next = tmp.next;
                tmp.next.pre = node;
            }
            tmp.next = node;
            node.pre = tmp;

        }

    }

    // 更新
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("链表为空。");
            return;
        }
        HeroNode2 tmp = head.next;
        boolean flag = false;
        while (true) {
            if (tmp ==  null) {
                break;
            }
            if (tmp.no == node.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.name = node.name;
            tmp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到编号为 %d 的节点，不能更新\n", node.no);
        }
    }

    /**
     * 删除
     * 直接找到这个节点，自我删除即可
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除。");
            return;
        }
        HeroNode2 tmp = head.next;
        boolean flag = false;
        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if(flag) {
            if (tmp.next == null) {
                tmp.pre.next = null;
            } else {
                tmp.pre.next = tmp.next;
                tmp.next.pre = tmp.pre;
            }
        } else {
            System.out.printf("没有找到编号为 %d 的节点，不能删除\n", no);
        }
    }

    // 遍历
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 tmp = head.next;
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

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 pre; // 指向前一个节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
