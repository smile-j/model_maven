package com.dong.base.test.design.templateMethodPattern;

import org.junit.Test;

/**
 * Created by Administrator on 2018/2/23.
 */
public class TemplateMethodPattern {

    //模版方法
    @Test
    public void test1(){
         int[] a = { 10, 32, 1, 9, 5, 7, 12, 0, 4, 3 }; // 预设数据数组
            AbstractSort s = new ConcreteSort();;
            s.showSortResult(a);
            s.sort(a);

    }

}
