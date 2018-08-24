package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/24
 *     desc   :
 *     面试题：输入一个整型数组，实现一个函数来调整该数组中的数字的顺序，
 *     使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分，希望时间复杂度尽量小。
 * </pre>
 */

class Test5 {

    public static void main(String[] args) {
        int[] array = new int[]{2, 4, 1, 3, 6, 8, 9};
        adjustOrder2(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
         adjustOrder2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] arr1 = {2, 4, 6, 8, 1, 3, 5, 7, 9};
        adjustOrder2(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        int[] arr2 = {2, 4, 6, 8, 10};
         adjustOrder2(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }

    /**
     * 解决思路：
     * 遍历数组，当遇到奇数时，
     * 局部变量保存当前奇数值，
     * 将前面的数进行 往后位移一位，
     * 然后把奇数值赋给第一位
     *
     * @param array
     */
    private static void adjustOrder(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("array is null");
        }
        if (array.length == 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int temp = 0;
            if (array[i] % 2 == 1) {
                temp = array[i];
                System.arraycopy(array, 0, array, 1, i);
                array[0] = temp;
            }

        }
    }

    /**
     * 优化思路：
     * 设置两个指针，一个从头，一个从尾
     * 当头指针发现了偶数，停下来，
     * 当尾指针发现了奇数，停下来，
     * 将头尾指针的值进行替换。
     * 在实际中，偶数跟奇数的 个数不可能相当，可能头指针跑得快，也可能尾指针跑得快，
     * 所以要确定遍历头指针的下标小于尾指针的下标
     *
     * @param array
     */
    private static void adjustOrder2(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("array is null");
        }
        if (array.length == 1) {
            return;
        }
        int odd = 0;
        int even = array.length - 1;
        int temp = 0;
        while (odd < even) {
            //遇到偶数就停下来
            while (odd < even && array[odd] % 2 != 0) {
                odd++;
            }
            //遇到奇数停下来
            while (odd < even && array[even] % 2 != 1) {
                even--;
            }
            //交换数值
            temp = array[odd];
            array[odd] = array[even];
            array[even] = temp;
        }
    }
}
