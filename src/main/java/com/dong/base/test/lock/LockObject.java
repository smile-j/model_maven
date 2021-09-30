package com.dong.base.test.lock;

public class LockObject {

    private String name ;
    private int age ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LockObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public LockObject(){

    }

    @Override
    public int hashCode() {
        return super.hashCode();
//        int result = 17;
//        result = result * 31 + name.hashCode();
//        result = result * 31 + age;
//F
//        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LockObject){
            LockObject o = (LockObject)obj;
            if(o.age==this.age&&this.getName().equals(o.getName())){
                return true;
            }
        }else {
            return false;
        }
        return false;
    }

}
