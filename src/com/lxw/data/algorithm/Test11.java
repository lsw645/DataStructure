package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/30
 *     desc   :
 *     题目：输入一个矩阵，按照从外向里以顺时针的顺序依次打印每一个数字。例如输入：
 *      {{1，2，3},
 *      4，5，6},
 *      7，8，9}}
 *      则依次打印数字为 1、2、3、6、9、8、7、4、5
 * </pre>
 */

class Test11 {

    public static void main(String[] args) {

    }


    private static void print(int[][] array) {
        if (array == null) {
            return;
        }
        //多少行
        int rows = array.length;
        //多少列的长度
        int colums = array[0].length;
        for (int i : array[0]) {

        }


        for (int i = 0; i < rows / 2 || i < colums / 2; i++) {
            for (int j = i; j < colums - i; j++) {
                System.out.print("行" + array[i][j]);
            }

            for (int j = i; j < rows - i; j++) {
                System.out.print("列" + array[i][j + 1]);
            }

            for (int j = colums - i; j > i; j--) {
                System.out.print("翻行" + array[rows - i][j - 2]);
            }

            for (int j = rows - i; j > i + 1; j--) {
                System.out.print("翻列" + array[j - 1][i]);
            }

        }
    }

    private static void prints(int[][] nums) {
        if (nums == null) {
            return;
        }
        int rows = nums.length;
        int columns = nums[0].length;
        int i = 0;
        while (2 * i + 1 < rows && 2 * i + 1 <= columns) {
            printRing(nums, i, rows, columns);
            ++i;
        }

    }

    private static void printRing(int[][] nums, int start, int rows, int columns) {
        int endRow = rows - 1 - start;
        int endCol = columns - 1 - start;

    }
}
