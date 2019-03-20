package com.edu.algorithm.sort;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 自己实现hashmap
 * Created by zhangxuan on 2019/3/20.
 */
public class Map<T> {

    private int size = 0;

    private int ss = 2;

    private double mul = 0.75;

    private Node<T>[] table = new Node[ss];

    private int hash(int key){
        return key%ss;
    }

    public T get(Integer key){
        int hash = hash(key);

        Node<T> node = table[hash];

        while (node != null){
            if (node.key.equals(key)){
                return node.data;
            }else {
                node = node.next;
            }
        }

        return null;

    }

    public void put(Integer key,T val){
        int hash = hash(key);
        Node node = table[hash];

        while (node != null){
            if (node.key.equals(key)){
                node.data = val;
                return;
            }else {
                node = node.next;
            }
        }
        if ((size+1) > (ss*mul)){
            resize();
        }

        int nhash = hash(key);
        Node node1 = table[nhash];
        table[nhash] = new Node<T>(key,val,node1);
        size++;

    }

    private void resize(){
        ss = ss<<1;
        Node<T>[] ntable = new Node[ss];

        for (int i = 0; i < table.length; i++) {
            Node<T> node = table[i];
            while (node != null){
                int hash = hash(node.key);
                Node node1 = ntable[hash];
                ntable[hash] = new Node<T>(node.key,node.data,node1);
                node = node.next;
            }

        }
        table = ntable;
    }

    class Node<T>{
        private Integer key;
        private T data;
        private Node next;

        public Node(Integer key, T data, Node next) {
            this.key = key;
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Map<Integer> map = new Map<>();
        map.put(1,1);
        map.put(17,1);
        map.put(18,1);
        map.put(19,1);

        map.put(1,2);
        map.put(127,2);
        map.put(147,2);
        map.put(117,2);
        Object o = map.get(1);
        System.out.println(o);
    }
}
