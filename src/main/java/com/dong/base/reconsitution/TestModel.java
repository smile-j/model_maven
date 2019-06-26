package com.dong.base.reconsitution;

import org.junit.Test;

public class TestModel {

    /**
     * 02：Inline Method （内联函数）& 03： Inline Temp(内联临时变量)
     */
    @Test
    public void testInLineMethod(){

        System.out.println(getNub()?2:1);

    }
    public boolean getNub(){
        return true;
    }

}
