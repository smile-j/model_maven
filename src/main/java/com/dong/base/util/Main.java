package com.dong.base.util;

import com.alibaba.fastjson.JSON;
import com.dong.base.model.UserEntity;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/9/24
 */
public class Main {

    public static void main(String[] args) {

        int arr[] =new int[]{3,7,9,1,3,2};
        quickSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    private static void quickSort(int[] arr, int left, int right){
        if (left >= right) return; //左数不小于右数，直接返回
        int pivot = arr[left]; //永远以最左边的数为基准
        int i = left, j = right;
        while (i < j){
            while (arr[j] >= pivot && i < j) --j; //从右向左遇到小于基准的数就停止
            while (arr[i] <= pivot && i < j) ++i; //从左向右遇到大于基准的数就停止
            if (i < j){ //交换
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //基准数归位（位置已确定）
        arr[left] = arr[i];
        arr[i] = pivot;
        //分别对基准数两端的子数组进行快排
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }


    public static void main2(String[] args) {
        List<UserEntity> users = Lists.newArrayList(
                UserEntity.builder().nickName("aa").id(5).loginCounts(1).build(),
                UserEntity.builder().nickName("bb").id(9).loginCounts(9).build(),
                UserEntity.builder().nickName("cc").id(3).loginCounts(11).build(),
                UserEntity.builder().nickName("dd").id(11).loginCounts(22).build(),
                UserEntity.builder().nickName("ee").id(12).loginCounts(21).build(),
                UserEntity.builder().nickName("ff").id(12).loginCounts(11).build(),
                UserEntity.builder().nickName("gg").id(12).loginCounts(2).build()
        );
        users.sort(new Comparator<UserEntity>() {
            @Override
            public int compare(UserEntity o1, UserEntity o2) {
                int ii = o1.getId().compareTo(o2.getId());
                if (ii == 0) {
                    ii = o1.getLoginCounts().compareTo(o2.getLoginCounts());
                }
                return ii;
            }
        });
        System.out.println(JSON.toJSON(users));
    }


}
