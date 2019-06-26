package com.dong.base.test.sort;

import org.junit.Test;

import java.util.Collections;

/**
 * 思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置，直到全部插入排序完为止。
 *
 * 插入排序：直接插入排序、二分法插入排序、希尔排序。
 *
 */
public class InsertSort {
    @Test
    public void testSort(){

        int[] a = { 49, 38, 65, 97, 176, 213, 227, 49, 78, 34, 12, 164, 11, 18, 1 };
        System.out.println("排序之前：");
        for(int o:a){
            System.out.print(o+ " ");
        }

        // 二分插入排序
        directInsertSort(a);
        System.out.println();
        System.out.println("排序之后：");
        for(int o:a){
            System.out.print(o+ " ");
        }

    }

    /**
     * 直接插入排序（从后向前找到合适位置后插入）
     * 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止
     */
    private static void directInsertSort(int [] a){
        for (int i = 1; i < a.length; i++) {
            // 待插入元素
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                // 将大于temp的往后移动一位
                if (a[j] > temp)
                {
                    a[j + 1] = a[j];
                }
                else
                {
                    break;
                }
            }
            a[j + 1] = temp;
        }
    }

    /**
     *  二分插入排序  从小到大  按二分法找到合适位置插入
     *  基本思想：二分法插入排序的思想和直接插入一样，只是找合适的插入位置的方式不同，这里是按二分法找到合适的位置，可以减少比较的次数
     *
     * @param a
     */
    private static void binarySort(int [] a){
        for (int i = 0; i < a.length; i++)
        {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right)
            {
                mid = (left + right) / 2;
                if (temp < a[mid])
                {
                    right = mid - 1;
                }
                else
                {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--)
            {
                a[j + 1] = a[j];
            }
            if (left != i)
            {
                a[left] = temp;
            }
        }
    }

    /**
     *  希尔排序
     *基本思想：先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
     *所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；然后，取第二个增量d2
     *
     * @param a
     */
    private static void shellSort(int[] a)
    {

        int d = a.length;
        while (true)
        {
            d = d / 2;
            for (int x = 0; x < d; x++)
            {
                for (int i = x + d; i < a.length; i = i + d)
                {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d)
                    {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
            if (d == 1)
            {
                break;
            }
        }

    }




 @Test
 public void test1(){
        int [] s = new int[]{1,2};
     binarySort(s);
 }





}
