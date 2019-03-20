package com.edu.algorithm.sort;

import java.util.LinkedList;

/**
 * Created by zhangxuan on 2019/3/20.
 */
public class DoubleLinked<T> {

    private Node<T> head;

    private Node<T> tail;

    public void add(T t){
        if (t == null){
            throw new NullPointerException("s");
        }
        Node<T> n = new Node<>(t, null, null);
        if (head == null && tail == null){
            head = n;
            tail = n;
        }else {
            n.pre = tail;
            tail.next = n;
            tail = n;
        }
    }

    class Node<T>{
        T data;
        Node<T> next;
        Node<T> pre;

        public Node(T data, Node<T> next, Node<T> pre) {
            this.data = data;
            this.next = next;
            this.pre = pre;
        }
    }

    public static void main(String[] args) {
        DoubleLinked<Integer> list = new DoubleLinked<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);

        }
        System.out.println(list);
    }

}
