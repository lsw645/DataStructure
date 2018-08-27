package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/27
 *     desc   :  选择排序  和  插入排序
 * </pre>
 */

class Test8 {
    public static void main(String[] args) {
        int[] array = {1, 3, 5, 22, 2, 6, 11, 20, 33, 1, 1, 1, 1};
        mergerSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    /**
     * 选择排序
     * 上述 java 代码可以看出我们除了交换元素并未开辟额外的空间，所以额外的空间复杂度为 O(1)。
     * <p>
     * 对于时间复杂度而言，选择排序序冒泡排序一样都需要遍历 n(n-1)/2 次,但是相对于冒泡排序来说每次遍历只需要交换一次元素，这对于计算机执行来说有一定的优化。但是选择排序也是名副其实的慢性子，即使是有序数组，也需要进行 n(n-1)/2 次比较，所以其时间复杂度为 O(n²)。
     * <p>
     * 即便无论如何也要进行 n(n-1)/2 次比较，选择排序仍是不稳定的排序算法，我们举一个例子如：序列 5 8 5 2 9， 我们知道第一趟选择第 1 个元素 5 会与 2 进行交换，那么原序列中两个 5 的相对先后顺序也就被破坏了。
     * <p>
     * 选择排序总结：
     * <p>
     * 选择排序的算法时间平均复杂度为O(n²)。
     * 选择排序空间复杂度为 O(1)。
     * 选择排序为不稳定排序。
     *
     * @param args 数组
     *             每次选择最小的数值在  数组左边
     */
    private static void selectSort(int[] args) {
        if (args == null) {
            return;
        }
        int length = args.length;
        int temp = 0;
        int index = 0;
        for (int i = 0; i < length - 1; i++) {
            temp = args[i];
            index = 0;
            for (int j = i + 1; j < length; j++) {
                if (temp > args[j]) {
                    temp = args[j];
                    index = j;
                }
            }
            if (index != 0) {
                args[index] = args[i];
                args[i] = temp;
            }
        }
    }

    /**
     * 插排
     * 对于插入的时间复杂度和空间复杂度，
     * 通过代码就可以看出跟选择和冒泡来说没什么区别同属于 O(n²) 级别的时间复杂度算法
     * ，只是遍历方式有原来的 n n-1 n-2 … 1，
     * 变成了 1 2 3 … n 了。
     * 最终得到时间复杂度都是 n(n-1)/2。
     * <p>
     * 对于稳定性来说，插入排序和冒泡一样，并不会改变原有的元素之间的顺序，
     * 如果遇见一个与插入元素相等的，那么把待插入的元素放在相等元素的后面。
     * 所以，相等元素的前后顺序没有改变，从原无序序列出去的顺序仍是排好序后的顺序，所以插入排序是稳定的。
     * <p>
     * 对于插入排序这里说一个非常重要的一点就是：由于这个算法可以提前终止内层比较（ arr[j-1] > arr[j]）所以这个排序算法很有用！因此对于一些 NlogN 级别的算法，后边的归并和快速都属于这个级别的，算法来说对于 n 小于一定级别的时候（Array.sort 中使用的是47）都可以用插入算法来优化,另外对于近乎有序的数组来说这个提前终止的方式就显得更加又有优势了。
     * <p>
     * 插入排序总结：
     * <p>
     * 插入排序的算法时间平均复杂度为O(n²)。
     * 插入排序空间复杂度为 O(1)。
     * 插入排序为稳定排序。
     * 插入排序对于近乎有序的数组来说效率更高，插入排序可用来优化高级排序算法
     *
     * @param args
     */
    private static void insertSort(int[] args) {
        if (args == null) {
            return;
        }

        int length = args.length;
        int i = 0, j = 0;
        int temp = 0;
        for (i = 1; i < length; i++) {
            temp = args[i];
            j = i;
            //从后往前遍历比较数值  如果大于要插入的值的话，就往后挪位置
            while (j > 0 && args[j - 1] > temp) {
                args[j] = args[j - 1];
                j--;
            }
            args[j] = temp;
        }

    }

    /**
     * 归并算法其实可以分为递归法和迭代法（自底向上归并），两种实现对于最小集合的归并操作思想是一样的。区别在于如何划分数组，我们先介绍下算法最基本的操作：
     *
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     *
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     *
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     *
     * 重复步骤 3 直到某一指针到达序列尾；
     *
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     *
     *
     * 归并排序算法总的时间复杂度是 O(nlogn)，而且这是归并排序算法中最好、最坏、平均的时间性能。
     *
     * 而由于在归并排序过程中需要与原始记录序列同样数量的存储空间存放归并结果以及递归时压入栈的数据占用的空间：n + logn，所以空间复杂度为 O(n)。
     *
     * 总结
     *
     * 归并排序虽然比较稳定，在时间上也是非常有效的，但是这种算法很消耗空间，
     * 一般来说只有在外部排序才会采用这个方法，
     * 但在内部排序不会用这种方法，而是用快速排序。
     *
     * @param args
     */
    private static void mergerSort(int[] args) {
        if (args == null) {
            return;
        }
        int end = args.length - 1;
        int start = 0;
        mergerSort(start, end, args);
    }

    private static void mergerSort(int start, int end, int[] args) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergerSort(start, mid, args);
        mergerSort(mid + 1, end, args);
        merger(start, mid, end, args);
//        merge(args, start, mid, end);
    }

    private static void merger(int start, int mid, int end, int[] args) {
        int[] temp = new int[end - start + 1];
        //第一个数组
        int start1 = start;
        int end1 = mid;
        //第二个数组
        int start2 = mid + 1;
        int end2 = end;
        //进行数组合并

        int index = 0;
        while (start1 <= end1 && start2 <= end2) {
            if (args[start1] <= args[start2]) {

                temp[index] = args[start1];
                start1++;
            } else {

                temp[index] = args[start2];
                start2++;
            }
            index++;
        }
        while (start1 <= end1) {
            temp[index] = args[start1];
            start1++;
            index++;
        }
        while (start2 <= end2) {
            temp[index] = args[start2];
            start2++;
            index++;
        }
        int temp2 = start;
        for (int i = 0; i < temp.length; i++) {
            args[temp2++] = temp[i];
        }

    }

    private static void merge(int[] arr, int start, int mid, int end) {
        // 先建立一个临时数组，用于存放排序后的数据
        int[] tmpArr = new int[end - start + 1];

        int start1 = start, end1 = mid, start2 = mid + 1, end2 = end;
        // 创建一个下标
        int pos = 0;
        // 缓存左边数组的第一个元素的索引
        int tmp = start1;
        while (start1 <= end1 && start2 <= end2) {
            // 从两个数组中取出最小的放入临时数组
            if (arr[start1] <= arr[start2])
                tmpArr[pos++] = arr[start1++];
            else
                tmpArr[pos++] = arr[start2++];
        }
        // 剩余部分依次放入临时数组，实际上下面两个 while 只会执行其中一个
        while (start1 <= end1) {
            tmpArr[pos++] = arr[start1++];
        }
        while (start2 <= end2) {
            tmpArr[pos++] = arr[start2++];
        }
        pos = 0;
        // 将临时数组中的内容拷贝回原来的数组中
        while (tmp <= end) {
            arr[tmp] = tmpArr[pos];
            tmp++;
            pos++;
        }

    }
}
