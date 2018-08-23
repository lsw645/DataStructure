package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/23
 *     desc   :
 *     面试题：一直青蛙一次可以跳上 1 级台阶，
 *     也可以跳上 2 级，求该青蛙跳上 n 级的台阶总共有多少种跳法。
 * </pre>
 */

class Test3 {

    public static void main(String[] args) {
        System.out.println(getJumpTypeNumber2(3));
    }

    /**
     * 分析：
     * 当台阶为1 时只有一种  1
     * 当台阶为2 时   1+1 ， 2
     * 台阶为3时，  1+1+1,1+2,2+1
     * 台阶为4时，   1+1+1+1， 1+2+1, 2+1+1, 1+1+2， 2+2
     * 台阶为5时     1+1+1+1+1 ,2+1+1+1, 1+2+1+1, 1+1+2+1, 1+1+1+2, 2+2+1，2+1+2，1+2+2，
     * 有数学归纳法得 f(n) = f(n-1)+ f(n-2)
     * 当n=1 时，得1， 当n=2时，得2
     *
     * @return 跳上N级台阶有多少总跳法
     */
    private static int getJumpTypeNumber(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return getJumpTypeNumber(n - 1) + getJumpTypeNumber(n - 2);
    }

    /**
     * 不适用递归实现
     *算法复杂度O(n)
     * @param n 多少级台阶
     * @return 跳上N级台阶有多少总跳法
     */
    private static int getJumpTypeNumber2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre2 = 1;
        int pre = 2;
        int result = -1;
        for (int i = 2; i < n; i++) {
            result = pre + pre2;
            pre2 = pre;
            pre = result;
        }
        return result;
    }
}
