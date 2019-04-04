package com.edu.algorithm;

/**
 * 目标target 求出他的平方根 要求误差范围可调
 * Created by zhangxuan on 2019/4/2.
 */
public class SquareRoot {

    /**
     *
     * @return
     */
    public static double squareRoot(int target,double accuracy){
        return getRoot(target,0.0,target,accuracy);

    }

    public static double getRoot(double target,double begin ,double end,double accuracy){

        while (true){
            double mid = (begin+end)/2;
            double square = mid*mid;

            if ((square > target && (square - target) < accuracy) || (square < target && (target - square) < accuracy) || square == target){
                return mid;
            }else if (square > target){
                end = mid;
            }else if (square < target){
                begin = mid;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(squareRoot(3,0.0000000001));
    }
}
