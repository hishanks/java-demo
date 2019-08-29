package com.demo.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author Shanks
 * @date 2019-06-11
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 6, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前：" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param arr   arr
     * @param start start
     * @param end   end
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            // 如果不止一个元素，继续划分两边递归排序下去
            int borderPosition = divide(arr, start, end);
            // 以某个基准值为界每一次划分元素排序后，此基准值所在位置
            System.out.printf("%2d ", borderPosition);
            System.out.println(Arrays.toString(arr));
            // 基准值左侧元素递归排序
            quickSort(arr, start, borderPosition - 1);
            // 基准值右侧元素递归排序
            quickSort(arr, borderPosition + 1, end);
        }
    }

    /**
     * 对数组某一段元素进行划分
     * 以基准值为界，左侧数都大于基准值，右侧数都大于基准值
     * 形象表示为：[{A|num<=基准值} 基准值 {B|num>=基准值}]
     *
     * @param arr   arr
     * @param start start
     * @param end   end
     * @return 每一次划分元素，基准值所在位置
     */
    public static int divide(int[] arr, int start, int end) {
        // 默认以最右边的元素作为基准值
        int base = arr[end];
        // 一旦start等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环
        while (start < end) {
            while (start < end && arr[start] <= base) {
                // 从左边开始向右遍历，如果如果遍历的值比基准值小，下标start就继续向右走
                start++;
            }
            // 上面的while循环结束时，就说明当前的arr[start]的值比基准值大，应与基准值位置进行交换
            if (start < end) {
                //交换
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                // 此值与基准值交换位置后，被“排序”，并且“占领”了原end的位置，排序范围缩小，end向前移动一位
                end--;
            }

            while (start < end && arr[end] >= base) {
                end--;// 从右边开始遍历，如果遍历的值比基准值大，下标end就继续向左走
            }
            // 上面的while循环结束时，就说明当前的arr[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                // 交换
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                // 此值与基准值交换后，被“排序”，并且“占领”原start的位置，排序范围缩小，所以start同时向后移动一位
                start++;
            }
        }
        // 返回start或end皆可，因为此时start和end都为基准值所在的位置
        return start == end ? start : -1;
    }
}