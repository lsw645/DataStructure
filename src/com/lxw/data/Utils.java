package com.lxw.data;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/09/22
 *     desc   :
 * </pre>
 */

public class Utils {

    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    public static void printArray(int[][] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                System.out.print(array[row][column]+" ");
            }
            System.out.println();
        }

    }
}
