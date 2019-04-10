package com.edu.algorithm;

import sun.misc.LRUCache;

import java.util.HashMap;

/**
 *
 * Created by zhangxuan on 2019/4/8.
 */
public class LRUCache1 {

    class LRUNode{
        LRUNode pre;
        LRUNode next;
        Integer key;
        Integer val;
    }

    private LRUNode root;
    private LRUNode tail;
    private HashMap<Integer,LRUNode> cache;
    private int capacity;

    public LRUCache1(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
    }

    public int get(int key){
        LRUNode lruNode = cache.get(key);

        if (lruNode == null){
            return -1;
        }
        moveNodeToRoot(lruNode);
        return lruNode.val;
    }

    private void moveNodeToRoot(LRUNode lruNode){
        if (lruNode == root) return;

        if (lruNode.pre != null){
            lruNode.pre.next = lruNode.next;
        }

        if (lruNode.next != null){
            lruNode.next.pre = lruNode.pre;
        }

        if (lruNode == tail){
            tail = tail.pre;
        }
        if (tail == null){
            root = tail = lruNode;
            return ;
        }
        lruNode.next = root;
        root.pre = lruNode;
        lruNode.pre = null;
        root = lruNode;
    }

    public void put(int key,int value){
        LRUNode lruNode = cache.get(key);
        if (lruNode == null){
            if (cache.size() >= capacity){
                removeTail();
            }
            lruNode = new LRUNode();
            lruNode.key = key;
        }
        lruNode.val = value;

        moveNodeToRoot(lruNode);
        cache.put(key,lruNode);
    }

    private void removeTail() {
        LRUNode p = tail;
        tail = tail.pre;
        if (tail != null){
            tail.next = null;
        }else {
            root = tail = null;
        }

        cache.remove(p.key);
    }

    public static void main(String[] args) {
        LRUCache1 lruCache1 = new LRUCache1(1);
        lruCache1.put(2,1);
        int i = lruCache1.get(2);
        lruCache1.put(3,2);
        int i1 = lruCache1.get(2);
        lruCache1.get(3);
    }

}
