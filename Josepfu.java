package com.atguigu.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否 ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);// 加入 5 个小孩节点
        circleSingleLinkedList.showBoy();
        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5); // 2->4->1->5->3
    }
}
class CircleSingleLinkedList {
    // 创建一个 first 节点,当前没有编号
    public Boy first = null;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        if(nums < 1){
            System.out.println("wrong input");
            return;
        }
        first = new Boy(1);
        Boy temp = first;
        for (int i = 2; i <= nums; i++) {
            Boy boy = new Boy(i);
            temp.next = boy;
            temp = temp.next;
        }
        temp.next = first;
    }
    // 遍历当前的环形链表
    public void showBoy() {
        if (first == null){
            System.out.println("null");
        }
        Boy temp = first;
        while (true){
            System.out.println(temp);
            if(temp.next == first){
                break;
            }
            temp = temp.next;
        }
    }
    // 根据用户的输入，计算出小孩出圈的顺序
    public void countBoy(int startNo, int countNum, int nums) {
        if(first == null || startNo <1 || startNo > nums){
            System.out.println("wrong input!");
            return;
        }
        Boy temp = first;//temp用来表示最后一个节点
        while (true){//让temp定位到最后一个节点
            if(temp.next == first){
                break;
            }
            temp = temp.next;
        }
        for (int i = 1; i < startNo; i++) {//找到第k个小孩
            temp = temp.next;
            first = first.next;
        }
        while(true){//开始报数的循环
            if(temp == first){
                break;
            }
            for (int i = 1; i < countNum; i++) {
                temp = temp.next;
                first = first.next;
            }
            System.out.println(first.no);
            first = first.next;
            temp.next = first;
        }
    }

}

// 创建一个 Boy 类，表示一个节点
class Boy {
    public int no;// 编号
    public Boy next; // 指向下一个节点,默认 null
    public Boy(int no) {
        this.no = no;
    }

}