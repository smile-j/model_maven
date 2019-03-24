package com.dong.base.test.collection;

public class Person  {
    private int age;
    private String name;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Person(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    public Person(){

    }
    /*static {
        System.out.println("静态代码块1！");
    }

    {
        System.out.println("代码块1！");
    }
    public Person(){
        System.out.println("构造方法！！！");
    }
    {
        System.out.println("代码块2！");
    }

    static {
        System.out.println("静态代码块2！");
    }*/

    @Override
    public String toString() {
        return "{person:"+"name:"+this.getName()+" age:"+this.getAge()+"}";
    }

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!name.equals(person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = age;
//        result = 31 * result + name.hashCode();
        return result;
    }*/


}
