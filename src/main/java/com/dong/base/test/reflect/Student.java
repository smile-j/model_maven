package com.dong.base.test.reflect;

/**
 * Created by Administrator on 2018/3/2.
 */

public class Student extends Person {

    private String school;
    private String grade;
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Student(String school, String grade) {
        super();
        this.school = school;
        this.grade = grade;
    }

    public Student(){
        System.out.println("stu有参构造器！");
    }
}

