package com.dong.base.test.quartztest;

/**
 * Created by Administrator on 2020/12/1.
 */
 class A {
    public void aa(){
        System.out.println("A.aa()");
        new C().cc();
    }
    public static void main(String[] args){
        System.out.println("**********************");
        new A().aa();
    }
}
    class B{
        public void bb(){
            System.out.println("B.bb()");
//            new C().cc();
        }
    }

    class C {
        public void cc(){
            System.out.println("C.cc()");
            new B().bb();
        }
    }
