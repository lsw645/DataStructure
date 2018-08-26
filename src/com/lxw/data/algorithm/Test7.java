package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/26
 *     desc   : 排序相关
 *
 *     排序算法的稳定性：
 *     通俗地讲就是能保证排序前两个相等的数据其在序列中的先后位置顺序
 *     与排序后它们两个先后位置顺序相同。
 * </pre>
 */

class Test7 {
    public static void main(String[] args) {
        int[] array = {1, 3, 5, 22, 2, 6, 11, 20, 33, 1, 1, 1, 1};
        realBubbleOrder2(array);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    /**
     * 错误的  冒泡排序
     *
     * @param intArray
     * @return
     */
    private static void bubbleOrder(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return;
        }

        int length = intArray.length;
        boolean isSort;
        for (int i = 0; i < length; i++) {
//            isSort = true;
            for (int j = i + 1; j < length - 1; j++) {
                if (intArray[i] > intArray[j]) {
                    int temp = intArray[i];
                    intArray[i] = intArray[j];
                    intArray[j] = temp;
                    isSort = false;
                }
            }
//            if (isSort) {
//                break;
//            }
        }
    }

    /*
    冒泡排序时间空间复杂度及算法稳定性分析

  对于长度为 n 的数组，冒泡排序需要经过 n(n-1)/2 次比较，
   最坏的情况下，即数组本身是倒序的情况下，
   需要经过 n(n-1)/2 次交换，所以其
  冒泡排序的算法时间平均复杂度为 O(n²)。空间复杂度为 O(1)。
     */
    private static void realBubbleOrder(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return;
        }
        int length = intArray.length;
        boolean isSort;
        for (int i = 0; i < length; i++) {
            isSort = true;
            for (int j = 1; j < length - i; j++) {
                if (intArray[j - 1] > intArray[j]) {
                    int temp = intArray[j - 1];
                    intArray[j - 1] = intArray[j];
                    intArray[j] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    /**
     * 我们试想一下这样的场景：假设有 100 个数字的数组，仅仅前 10 个无序，后面 90 个均有序并且都大于前面 10 个数字。
     *
     * 我们采用上面的终极算法可以明显看到，第一趟排序后，最后发生交换的位置必定大于 10，且这个位置之后的数据必定已经有序了，但我们还是会去做徒劳的 90 次遍历，而且我们还要遍历 10 次！
     *
     * 显然我们可以找到这样的思路，在第一次排序后，就记住最后发生交换的位置，第二次只要从数组头部遍历到这个位置就 OK 了。
     * @param intArray
     */
    private static void realBubbleOrder2(int[] intArray) {
        if (intArray == null || intArray.length == 0) {
            return;
        }
        int length = intArray.length;
        int flag = length;
        int k = 0;
        for (int i = 0; i < length-1; i++) {
            k = flag;
            flag = 0;
            for (int j = 1; j < k; j++) {
                if (intArray[j - 1] > intArray[j]) {
                    int temp = intArray[j - 1];
                    intArray[j - 1] = intArray[j];
                    intArray[j] = temp;
                    flag = j;
                }
            }
            if (flag == 0) {
                break;
            }
        }


    }

}