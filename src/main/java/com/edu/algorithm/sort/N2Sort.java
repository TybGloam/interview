package com.edu.algorithm.sort;

/**
 * Created by zhangxuan on 2019/3/18.
 */
public class N2Sort {

    public static void bobo(int[] is){
        for (int i = 0; i < is.length; i++) {
            //因为冒泡是排到队尾所以需要-i
            for (int j = 0; j < is.length-1-i; j++) {
                if (is[j] > is[j+1]){
                    int tmp = is[j+1];
                    is[j+1] = is[j];
                    is[j] = tmp;
                }
            }
        }
    }

    /**
     * 选择
     * @param is
     */
    public static void chang(int[] is){
        for (int i = 0; i < is.length; i++) {
            int min = i;
            for (int j = i; j < is.length; j++) {
                if (is[min] > is[j]) {
                    min = j;
                }

            }
            int tmp = is[min];
            is[min] = is[i];
            is[i] = tmp;

        }
    }

    public static void insert(int[] is){
        for (int i = 0; i < is.length; i++) {

        }
    }

    public static void main(String[] args) {
        int[] is = {1,532,3,65,4,4,7,85,6};
        chang(is);
        for (int i : is) {
            System.out.print(i+" ");
        }
    }
}
