package com.edu.algorithm.sort;

/**
 *
 * Created by zhangxuan on 2019/3/20.
 */
public class ArrayLists<T> {

    private int size = 0;

    private volatile int no = 2 ;
    private Object[] table = new Object[no];

    private double yinzi = 0.75;


    public T get(int index){
        if (index > size){
            throw new ArrayIndexOutOfBoundsException("数组下标越界");
        }
        return (T) table[index];
    }

    public void add(T t){
        if (t == null){
            throw new RuntimeException("不可以插入null");
        }
        if (size > (no*yinzi)){
            resize();
        }
        table[size++] = t;

    }

    private void resize(){
        no = no << 1;

        Object[] nts = new Object[no];

        for (int i = 0; i < table.length; i++) {
            nts[i] = table[i];
        }
        table = nts;

    }

    public static void main(String[] args) {
        ArrayLists<Integer> list = new ArrayLists<>();
        for (int i = 0; i < 55; i++) {
            list.add(i);
        }
        System.out.println(list);
    }

}
