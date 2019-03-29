package com.edu.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树,本case使用查询二叉树
 * 前中后序均为循环遍历
 */
public class BinaryTree {
    Node root;

    class Node {
        int data;
        Node lChild;
        Node rChild;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 前序
     * 先寻找到最左节点 依次压栈 压栈前输出
     * 之后pop出栈后把
     * @return
     */
    public List<Integer> bef(){
        List<Integer> list = new ArrayList<Integer>();
        Stack<Node> stack = new Stack<Node>();

        Node p = root;
        while (p != null || !stack.isEmpty()){
            while (p != null){
                list.add(p.data);
                stack.push(p);
                p = p.lChild;
            }
            if (!stack.isEmpty()){
                Node pop = stack.pop();
                p = pop.rChild;
            }
        }
        return list;
    }

    /**
     * 中序
     *
     * @return
     */
    public List<Integer> mid(){
        List<Integer> list = new ArrayList<Integer>();
        Stack<Node> stack = new Stack<Node>();
        Node p = root;
        while(p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.lChild;
            }
            if (!stack.isEmpty()){
                Node pop = stack.pop();
                list.add(pop.data);
                p = pop.rChild;
            }
        }
        return list;
    }

    public List<Integer> end(){
        List<Integer> list = new ArrayList<Integer>();
        Stack<Node> stack = new Stack<Node>();

        if (root == null){
            return list;
        }

        stack.push(root);
        Node cur,pre = null;
        while (!stack.isEmpty()){
            cur = stack.peek();
            //当前节点为叶子节点时或者前一个节点
            if ((cur.lChild == null && cur.rChild == null) || (pre != null && (cur.rChild == pre || cur.lChild == pre))){
                Node pop = stack.pop();
                list.add(pop.data);
                pre = pop;

            }else {
                if (cur.rChild != null){
                    stack.push(cur.rChild);
                }
                if (cur.lChild != null){
                    stack.push(cur.lChild);
                }
            }


        }
        return list;
    }

    public List tail(){
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        if (root == null) return null;
        Node cur,pre = null;
        stack.push(root);
        while (!stack.isEmpty()){
            cur = stack.pop();
            if ((cur.lChild == null && cur.rChild == null) || (pre != null &&(pre == cur.lChild || pre == cur.rChild))){
                result.add(cur.data);
                pre = cur;
            }else {
                stack.push(cur);
                if (cur.rChild != null){
                    stack.push(cur.rChild);
                }
                if (cur.lChild != null){
                    stack.push(cur.lChild);
                }
            }
        }
        return result;
    }


    public void put(int data){
        //如果root为空时
        if (root == null){
            root = new Node(data);
            return;
        }

        Node p = root;
        Node parent = null;

        while (p != null){
            parent = p;
            if (data < p.data){
                p = p.lChild;
            }else if (data == p.data){
                return;
            }else{
                p = p.rChild;
            }
        }

        if (data > parent.data){
            parent.rChild = new Node(data);
        }else {
            parent.lChild = new Node(data);
        }
    }

    public static void main(String[] args) {
        int[] is = {12,3,2,13,4,42,23,5};
        BinaryTree binaryTree = new BinaryTree();
        for (int i : is) {
            binaryTree.put(i);
        }

        List<Integer> mid = binaryTree.mid();
        System.out.println(mid);
        List<Integer> bef = binaryTree.bef();
        System.out.println(bef);
        System.out.println(binaryTree.tail());
        List<Integer> end = binaryTree.end();
        System.out.println(end);
    }

}
