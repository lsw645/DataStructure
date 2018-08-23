package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/23
 *     desc   :
 *     尝试实现 Java 的 Math.pow(double base,int exponent) 函数算法，
 *     计算 base 的 exponent 次方，不得使用库函数，同时不需要考虑大数问题。
 * </pre>
 */

class Test4 {
    public static void main(String[] args) {
        System.out.println(pow2(2, 2));
        System.out.println(pow2(1, 2));
        System.out.println(pow2(2, -2));
        System.out.println(pow2(0, 2));
        System.out.println(pow2(2, -3));
    }

    /**
     * 这道题考的不是 难度，考的是细节，如下：
     * 1.注意输入进来的 exponent是负数 返回的是 倒数
     * 2.注意 base输入进来的是 0
     *
     * @param base     底数
     * @param exponent 次方数
     * @return
     */
    private static float pow(int base, int exponent) {
        float result = 1.0f;
        if (base == 0) {
            return result;
        }
        boolean isNegative = false;
        if (exponent < 0) {
            isNegative = true;
            exponent = -exponent;
        }
//        result = base;

        for (int i = 0; i < exponent; i++) {
            result = base * result;
        }
        if (isNegative) {
            return 1 / result;
        }
        return result;
    }


    /**
     * 如何优化
     * 思路：
     * 2^4 = (2^2)^2
     * 2^8 = (2^4)^2
     * 2^9 = (2^4)^2*2
     *
     * @param base     底数
     * @param exponent 次方数
     * @return
     */
    private static float pow2(int base, int exponent) {
        float result = 1.0f;
        if (base == 0) {
            return result;
        }
        boolean isNegative = false;
        if (exponent < 0) {
            isNegative = true;
            exponent = -exponent;
        }
        //只需要求出一半的值，然后result^2
        for (int i = 0; i < exponent / 2; i++) {
            result = base * result;
        }
        result = result * result;
        //判断是否为基数
        if ((exponent & 0x01) == 1) {
            result = result * base;
        }

        if (isNegative) {
            return 1 / result;
        }
        return result;
    }
}
