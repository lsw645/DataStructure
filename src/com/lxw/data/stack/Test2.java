package com.lxw.data.stack;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/22
 *     desc   :题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *     输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 *     例如数组 {3，4，5，1，2} 为数组 {1，2，3，4，5} 的一个旋转，该数组的最小值为 1。
 * </pre>
 */

class Test2 {

    public static void main(String[] args) {
        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(getMinimum(array1));

        int[] array2 = {3, 4, 5, 1, 1, 2};
//        System.out.println(getMinimum(array2));
//
//        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
//        System.out.println(getMinimum(array3));
//
//        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
//        System.out.println(getMinimum(array4));
//
//        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int[] array5 = {1, 2, 3, 4, 5};
//        System.out.println(getMinimum(array5));
//
//        // 数组中只有一个数字
        int[] array6 = {2};
//        System.out.println(getMinimum(array6));
//
//        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
//        System.out.println(getMinimum(array7));
        System.out.println(getMinimumByDichotomy(array4));
    }

    private static int getMinimum(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("array is null or array size is zero");
        }
        int min = Integer.MAX_VALUE;
        for (int number : array) {
            if (min > number) {
                min = number;
            }
        }
        return min;
    }

    /**
     * 通过二分查找旋转数组获取最小组
     *
     * @param array 数组
     * @return 最小值
     */
    private static int getMinimumByDichotomy(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("array is null or array size is zero");
        }
        if (array.length == 1) {
            return array[0];
        }


        int low = 0;
        int high = array.length - 1;
//        while (high - low > 0) {
//            int mid = (low + high) / 2;
//            //说明中间值在第一个递增数组
//            if (array[low] < array[mid]) {
//                low = mid;
//            } else if (array[high] > array[mid]) {
//                high = mid;
//            }
//        }
        int result = array[0];
        //保证数组 low在左边的递增数组  hight在右边的递增数组
        while (array[low] >= array[high]) {
            int mid = (low + high) / 2;
            //但遇到值都相等的特殊情况
            if (array[low] == array[mid] && array[high] == array[high]) {
                return midInOrder(array, low, high);
            }
            //说明中间值在第一个递增数组
            if (array[low] < array[mid]) {
                low = mid;
            } else if (array[high] > array[mid]) {
                high = mid;
            }
        }
        return result;
    }

    private static int midInOrder(int[] nums, int start, int end) {
        int result = nums[start];
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] < result) {
                result = nums[i];
            }
        }
        return result;
    }
}
