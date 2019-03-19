package com.edu.algorithm.sort;

/**
 * Created by zhangxuan on 2019/3/18.
 */
public class QuickSort {

    public static void quickSort(int[] is){
        sort(is,0,is.length-1);
    }

    private static void sort(int[] is,int start,int end){
        if (start >= end){
            return;
        }
        int p = getP(is,start,end);

        sort(is,start,p-1);
        sort(is,p+1,end);

    }

    private static int getP(int[] is,int start,int end){
        int no = is[end];
        int p = start;
        for (int i = start; i < end; i++) {
            if (is[i] < no){
                if (i != p){
                    int tmp = is[i];
                    is[i] = is[p];
                    is[p] = tmp;
                }
                p++;
            }
        }

        int tmp = is[p];

        is[p] = no;
        is[end] = tmp;
        return p;

    }

    public static void main(String[] args) {
        int[] is = {4,12,3,2,1,232,1,23,1,23,2,1,31,23,1};

        quickSort(is);

        for (int i : is) {
            System.out.print(i+" ");
        }
    }
}
