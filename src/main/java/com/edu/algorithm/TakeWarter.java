package com.edu.algorithm;

/**
 *
 * Created by zhangxuan on 2019/4/8.
 */
public class TakeWarter {
    public static int trap(int[] height) {
        int result = 0;
        if (height == null) return 0;
        int maxIndex = 0;

        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[maxIndex]){
                maxIndex = i;
            }
        }

        int preMaxIndexL = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (height[i] >= height[preMaxIndexL]){
                preMaxIndexL = i;
            }else {
                result += height[preMaxIndexL]-height[i];
            }

        }
        int preMaxIndexR = height.length-1;
        for (int i = height.length-1; i > maxIndex;i--){
            if (height[i] >= height[preMaxIndexR]){
                preMaxIndexR = i;
            }else {
                result += height[preMaxIndexR]-height[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] is = {4,3,2};
        int trap = trap(is);
        System.out.println(trap);
    }
}
