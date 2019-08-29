package com.demo.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Shanks
 * @date 2019-06-10
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 6, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前：" + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------");

        int[] brr = new int[]{3, 44, 38, 6, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前：" + Arrays.toString(brr));
        bubbleSortWithSorted(brr);
        System.out.println("排序后：" + Arrays.toString(brr));

        System.out.println("====================================================================================================");
        System.out.println("====================================================================================================");

        int[] crr = new int[]{3, 44, 38, 6, 47, 15, 36, 45, 50, 55, 60, 65, 70, 75};
        System.out.println("排序前：" + Arrays.toString(crr));
        bubbleSortWithSortBorder(crr);
        System.out.println("排序后：" + Arrays.toString(crr));

        System.out.println("****************************************************************************************************");
        System.out.println("****************************************************************************************************");
        int[] drr = new int[]{3, 44, 38, 6, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前：" + Arrays.toString(drr));
        bubbleSortWithCocktail(drr);
        System.out.println("排序后：" + Arrays.toString(drr));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.printf("\t 第 %2d 次，第 %2d 轮排序后：%s%n", i + 1, j + 1, Arrays.toString(arr));
            }
            System.out.printf("第 %2d 次排序结果：%s%n", i + 1, Arrays.toString(arr));
        }
    }

    /**
     * 该方法是通用的方法，优化了数组不必要排序的场景，即执行到中间某一次排序时，数组已经是有序的，后面的几次排序无需进行了
     *
     * @param arr int[]
     */
    public static void bubbleSortWithSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 因为有数据交换，所以不是有序的，标记设为false
                    isSorted = false;
                }
                System.out.printf("\t 第 %2d 次，第 %2d 轮排序后：%s%n", i + 1, j + 1, Arrays.toString(arr));
            }
            // 如果在排序过程中，某次排序时，数据已经有序，则直接break跳出整个外层循环，即后面没轮到的几次排序无需进行了
            if (isSorted) {
                System.out.printf("第 %2d 次排序（数组已有序，直接退出循环）：%s%n", i + 1, Arrays.toString(arr));
                break;
            }
            System.out.printf("第 %2d 次排序：%s%n", i + 1, Arrays.toString(arr));
        }
    }

    /**
     * 该方法可能只适用于一些特殊的数组，例如该数组：{3, 44, 38, 6, 47, 15, 36, 45, 50, 55, 60, 65, 70, 75}
     * 可以看出，该数组的前半部分元素无无序，后半部分有序且后半部分最小值也大于前半部分的最大值，如下示例数组
     *
     * @param arr int[]
     */
    @Deprecated
    public static void bubbleSortWithSortBorder(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSorted = true;
            // 无序数列的边界，每次比较只需比到这里为止
            int sortBorder = arr.length - 1;
            for (int j = 0; j < sortBorder; j++) {
                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 因为有数据交换，所以不是有序的，标记设为false
                    isSorted = false;
                    // 把无序数列的边界更新为最后一次数据交换的位置
                    sortBorder = j;
                }
                System.out.printf("\t 第 %2d 次，第 %2d 轮排序后：%s%n", i + 1, j + 1, Arrays.toString(arr));
            }
            // 如果在排序过程中，某次排序时，数据已经有序，则直接break跳出整个外层循环，即后面没轮到的几次排序无需进行了
            if (isSorted) {
                System.out.printf("第 %2d 次排序（数组已有序，直接退出循环）：%s%n", i + 1, Arrays.toString(arr));
                break;
            }
            System.out.printf("第 %2d 次排序：%s%n", i + 1, Arrays.toString(arr));
        }
    }

    /**
     * 鸡尾酒排序，基于冒泡排序的一种升级排序法，鸡尾酒排序的元素比较和交换的过程是双向的
     *
     * @param arr int[]
     */
    public static void bubbleSortWithCocktail(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            boolean isSorted = true;
            // 奇数轮
            for (int j = i; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            // 在偶数轮之前，将isSorted重新标记为true
            isSorted = true;
            for (int j = arr.length - i - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }
}