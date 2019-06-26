package com.dong.base.test.design.templateMethodPattern;

/**
 * Created by Administrator on 2018/2/23.
 */
 public abstract class AbstractSort {

    protected abstract void sort(int[] array);
    public final void showSortResult(int[] array) {
        this.sort(array);
        System.out.print("排序结果：");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%3s", array[i]);
        }
    }
}
