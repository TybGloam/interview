package com.edu.algorithm.sort;

/**
 *
 * 归并排序
 * Created by zhangxuan on 2019/4/1.
 */
public class MergeSort {

    public static int[] sort(int[] is){
        return mergeSort(is,0,is.length-1);
    }

    public static int[] mergeSort(int[] is,int start , int end){
        if (start >= end){
            int[] isss= {is[start]};
            return isss;
        }
        int mid = (end+start)/2;
        int[] ints = mergeSort(is, start, mid);
        int[] ints1 = mergeSort(is, mid + 1, end);

        int[] result = new int[ints.length+ints1.length];

        int i=0,j = 0,k=0;
        while (i < ints.length && j<ints1.length){
            if (ints[i] >= ints1[j]){
                result[k] = ints[i];
                i++;
            }else {
                result[k] = ints1[j];
                j++;
            }
            k++;
        }

        if (i < ints.length){
            while (i < ints.length){
                result[k++] = ints[i++];
            }
        }else {
            while (j < ints1.length){
                result[k++] = ints1[j++];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] is = {1,2,3,2,1,23,232,12,21,2,1,3,2,12,21,34,2,32};
        int[] sort = sort(is);
        for (int i : sort) {
            System.out.print(i+" ");
        }
    }
}
