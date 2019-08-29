package com.demo.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author Shanks
 * @date 2019-06-17
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 6, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int maxValue = arr[i];
                    arr[i] = arr[j];
                    arr[j] = maxValue;
                }
                System.out.printf("\t 第 %2d 次排序，内层第 %2d 轮排序后：\t %s%n", i + 1, j, Arrays.toString(arr));
            }
            System.out.printf("第 %2d 次排序：%s%n", i + 1, Arrays.toString(arr));
        }
    }
}