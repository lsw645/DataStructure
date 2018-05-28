package com.lxw.data.sort;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/05/28
 *     desc   :
 * </pre>
 */

class Sort {

    public static void main(String[] args) {

    }

    private static void insertSort(int [] array){
        for(int i=0; i<array.length;i++){
            for(int j = i;j>0;j--){
                if(array[j-1]>array[j]){
                    int temp = array[j-1];
                    array[j-1] =array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param array 数组
     */
    private static void bubleSort(int[] array) {
        for (int j = array.length - 1; j > 0; j--) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

    private static void selectionSort(int[] array) {
        int length = array.length;
        int position = 0;
        int temp = 0;
        for (int i = 0; i < length - 1; i++) {
            int min = array[i];
            for (int j = i + 1; j < length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    array[j] = array[i];
                    array[i]=min;
                }
            }
        }
    }
}
