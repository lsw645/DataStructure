package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/28
 *     desc   : 快速排序
 * </pre>
 */

class Test9 {
    public static void main(String[] args) {
//        int[] arr = {6, 4, 3, 2, 7, 9, 1, 8, 5};
//        quickSort(arr, 0, arr.length - 1);
//        printArray(arr);
        int[] array = {1, 3, 5, 22, 2, 6, 11, 20, 33};
        long start = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        System.out.println("quick1:" + (System.nanoTime() - start));
        printArray(array);
        start = System.nanoTime();
        int[] array2 = {1, 3, 5, 22, 2, 6, 11, 20, 33};
        quickSort2(array2, 0, array.length - 1);
        System.out.println("quick2:" + (System.nanoTime() - start));
        printArray(array2);
    }

    /**
     * 快排算法
     * <p>
     * 暂不支持有重复数字的 数组
     * <p>
     * 算法进行一下时间复杂度的分析：
     * <p>
     * 最好情况
     * <p>
     * 在最好的情况下，每次我们进行一次分区，我们会把一个序列刚好分为几近相等的两个子序列，这个情况也我们每次递归调用的是时候也就刚好处理一半大小的子序列。这看起来其实就是一个完全二叉树，树的深度为 O(logn)，所以我们需要做 O(logn) 次嵌套调用。但是在同一层次结构的两个程序调用中，不会处理为原来数列的相同部分。因此，程序调用的每一层次结构总共全部需要 O(n) 的时间。所以这个算法在最好情况下的时间复杂度为 O(nlogn)。
     * <p>
     * 事实上，我们并不需要如此精确的分区：即使我们每个基准值把元素分开为 99% 在一边和 1% 在另一边。调用的深度仍然限制在 100logn，所以全部运行时间依然是 O(nlogn)。
     * <p>
     * 最坏情况
     * <p>
     * 事实上，我们总不能保证上面的理想情况。试想一下，假设每次分区后都出现子序列的长度一个为 1 一个为 n-1，那真是糟糕透顶。这一定会导致我们的表达式变成：
     * <p>
     * T(n) = O(n) + T(1) + T(n-1) = O(n) + T(n-1)
     * <p>
     * 这和插入排序和选择排序的关系式真是如出一辙，所以我们的最坏情况是 O(n²)。
     * <p>
     * <p>
     * 找到更好的基准数
     * <p>
     * 上面对时间复杂度进行了简要分析，可见我们的时间复杂度和我们的基准数的选择密不可分。基准数选好了，把序列每次都能分为几近相等的两份，我们的快排就跟着吃香喝辣；但一旦选择的基准数很差，那我们的快排也就跟着穷困潦倒。
     * <p>
     * 所以大家就各显神通，出现了各种选择基准数的方式。
     * <p>
     * 固定基准数
     * <p>
     * 上面的那种算法，就是一种固定基准数的方式。如果输入的序列是随机的，处理时间还相对比较能接受。但如果数组已经有序，用上面的方式显然非常不好，因为每次划分都只能使待排序序列长度减一。这真是糟糕透了，快排沦为冒泡排序，时间复杂度为 O(n²)。因此，使用第一个元素作为基准数是非常糟糕的，我们应该立即放弃这种想法。
     * <p>
     * 随机基准数
     * <p>
     * 这是一种相对安全的策略。由于基准数的位置是随机的，那么产生的分割也不会总是出现劣质的分割。但在数组所有数字完全相等的时候，仍然会是最坏情况。实际上，随机化快速排序得到理论最坏情况的可能性仅为1/(2^n）。所以随机化快速排序可以对于绝大多数输入数据达到 O(nlogn) 的期望时间复杂度。
     * <p>
     * 三数取中
     * <p>
     * 虽然随机基准数方法选取方式减少了出现不好分割的几率，但是最坏情况下还是 O(n²)。为了缓解这个尴尬的气氛，就引入了「三数取中」这样的基准数选取方式。
     *
     * @param array 需要排序的数组
     * @return 基准值
     */
    private static void quickSort(int[] array, int left, int right) {
        if (array == null || left >= right) {
            return;
        }
        int mid = partition(array, left, right);
        quickSort(array, left, mid);
        quickSort(array, mid + 1, right);


    }

    private static int partition(int[] array, int left, int right) {
        int temp = array[left];
        //保证左下标小于右下标
        while (left < right) {
            //从right开始找，找到小于等于temp的值
            while (left < right && array[right] > temp) {
                right--;
            }
            //找到之后，如果left小于等于right
            if (left <= right) {
                array[left] = array[right];
            }
            //从left找，找到大于temp的值
            while (left < right && array[left] <= temp) {
                left++;
            }
            if (left <= right) {
                array[right] = array[left];
            }
        }
        array[left] = temp;
        return left;
    }


    private static void quickSort2(int[] array, int left, int right) {
        if (array == null || left >= right) {
            return;
        }
        int mid = partition2(array, left, right);
        quickSort(array, left, mid);
        quickSort(array, mid + 1, right);


    }

    private static void swap(int[] array, int left, int right) {
        if (array == null) {
            return;
        }
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private static int partition2(int[] array, int left, int right) {
        int mid = (left + right) / 2;
        //保证左端值比 右端值小
        if (array[left] > array[right]) {
            swap(array, left, right);
        }
        //保证 中间值最小
        if (array[left] < array[mid]) {
            swap(array, left, mid);
        }
        //保证右边值最大
        if (array[mid] > array[right]) {
            swap(array, mid, right);
        }


        int temp = array[left];
        //保证左下标小于右下标
        while (left < right) {
            //从right开始找，找到小于等于temp的值
            while (left < right && array[right] > temp) {
                right--;
            }
            //找到之后，如果left小于等于right
            if (left <= right) {
                array[left] = array[right];
            }
            //从left找，找到大于temp的值
            while (left < right && array[left] <= temp) {
                left++;
            }
            if (left <= right) {
                array[right] = array[left];
            }
        }
        array[left] = temp;
        return left;
    }


    private static void printArray(int[] array) {
        if (array == null) {
            return;
        }

        for (int i : array) {
            System.out.print(i + ", ");
        }

    }


}
