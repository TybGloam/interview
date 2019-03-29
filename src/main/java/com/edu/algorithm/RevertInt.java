package com.edu.algorithm;

/**
 * Created by zhangxuan on 2019/3/27.
 */
public class RevertInt {

    public static int revert(int i) throws Exception {
        int result = 0;
        while (i != 0){
            int v = i % 10;
            i = i/10;
            if (result > (Integer.MAX_VALUE/10)){
                throw new Exception("超出int最大值");
            }else if (result == (Integer.MAX_VALUE/10)&&v > 7){

            }else {
                result = result*10 +v;

            }

        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(revert(0));
        System.out.println(revert(-1234));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(revert(Integer.MAX_VALUE));
    }
}
