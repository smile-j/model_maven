package com.dong.base.test.jvm;

/**
 * Created by Administrator on 2017/11/27.
 */
public class FinalizedEscapeTestCase {
    public static FinalizedEscapeTestCase caseForEscape = null;

    @Override

    protected void finalize() throws Throwable {

        super.finalize();

        System.out.println("哈哈，我已逃逸！");

        caseForEscape = this;

    }
}
