package algorithm;

import java.util.Arrays;


public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 7, 9};
        insertSort(arr);
    }

    /**
     * 插入排序
     * @param arr 需要排序的数组
     */
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
