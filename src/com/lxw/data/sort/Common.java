package com.lxw.data.sort;

import com.lxw.data.Utils;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/09/22
 *     desc   :冒泡、插入、选择排序
 * </pre>
 */

class Common {
    private static int[] arg = new int[]{2, 1, 2, 3, 4, 5, 6};

    public static void main(String[] args) {
        shellSort(arg);
        Utils.printArray(arg);
    }

    /**
     * 冒泡排序，从左到右，与邻边的数字不断比较，把较大的值放到数组的最右边
     */
    private static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int temp;
        boolean isSort = false;
        for (int i = 0; i < array.length; i++) {
            isSort = true;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    //选择排序就是  每次把数字放到最左边
    private static void selectionSort(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return;
        }
        int index;
        int temp;
        for (int i = 0; i < numbers.length; i++) {
            index = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[index] > numbers[j]) {
                    index = j;
                }
            }
            temp = numbers[index];
            numbers[index] = numbers[i];
            numbers[i] = temp;
        }

    }


    /**
     * 插入排序， 就是往已经排好序的数组中，从后往前进行比较，然后插入数字
     *
     * @param numbers
     */
    private static void insertSort(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return;
        }
        int temp;
        int j;
        for (int i = 1; i < numbers.length; i++) {
            temp = numbers[i];
            for (j = i; j > 0 && (temp < numbers[j - 1]); j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = temp;
        }
    }

    private static void quickSort(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return;
        }
        quickSort(numbers, 0, numbers.length - 1);
    }

    private static void quickSort(int[] numbers, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = partition(numbers, start, end);
        quickSort(numbers, start, middle - 1);
        quickSort(numbers, middle + 1, end);

    }

    private static int partition(int[] numbers, int start, int end) {
        int temp = numbers[start];
        int low = start;
        int height = end;

        while (height > low) {
            while (numbers[height] >= temp && height > low) {
                height--;
            }
            numbers[low] = numbers[height];
            while (numbers[low] <= temp && height > low) {
                low++;
            }
            numbers[height] = numbers[low];
        }
        numbers[low] = temp;
        return low;
    }

    private static void mergeSort(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return;
        }
        mergeSort(numbers, 0, numbers.length - 1);
    }

    private static void mergeSort(int[] numbers, int low, int height) {
        if (height <= low) {
            return;
        }
        int middle = (low + height) >> 1;
        mergeSort(numbers, low, middle);
        mergeSort(numbers, middle + 1, height);
        merge(numbers, low, middle, height);
    }

    private static void merge(int[] numbers, int low, int middle, int height) {
        int[] newArray = new int[height - low + 1];
        int left = low;
        int right = middle + 1;
        int count = 0;
        while (left <= middle && right <= height) {
            if (numbers[left] <= numbers[right]) {
                newArray[count++] = numbers[left++];
            } else {
                newArray[count++] = numbers[right++];
            }
        }

        while (left <= middle) {
            newArray[count++] = numbers[left++];
        }
        while (right <= height) {
            newArray[count++] = numbers[right++];
        }
        for (int i = 0; i < newArray.length; i++) {
            numbers[low + i] = newArray[i];
        }
    }


    private static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        //建堆
        createHeap(array);
        int temp;
        //不断将头部最大值与底部交换，并进行调整堆
        for (int i = array.length - 1; i > 0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adujstHeap(array, i, 0);
        }
    }

    private static void createHeap(int[] array) {
        int startIndex = (array.length - 1) >> 1;
        for (int i = startIndex; i >= 0; i--) {
            adujstHeap(array, array.length, i);
        }
    }

    private static void adujstHeap(int[] array, int n, int k) {
        //获取左右根节点
        int left = (k << 1) + 1;
        int right = (k << 1) + 2;
        if (left >= n && right >= n) {
            return;
        }
        int leftValue = Integer.MIN_VALUE;
        int rightValue = Integer.MIN_VALUE;
        if (left < n) {
            leftValue = array[left];
        }
        if (right < n) {
            rightValue = array[right];
        }

        if (array[k] >= leftValue && array[k] >= rightValue) {
            return;
        }

        if (leftValue > rightValue) {
            array[left] = array[k];
            array[k] = leftValue;
            adujstHeap(array, n, left);
        } else {
            array[right] = array[k];
            array[k] = rightValue;
            adujstHeap(array, n, right);
        }
    }

    private static void shellSort(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            return;
        }
        int temp;
        int j;
        for (int path = numbers.length >> 1;
             path > 0; path = path >> 1) {

            for (int i = path; i < numbers.length; i = i + path) {

                temp = numbers[i];

                for (j = i; j > 0 && temp < numbers[j - path]; j -= path) {
                    numbers[j] = numbers[j - path];
                }
                numbers[j] = temp;
            }
        }


    }


}
