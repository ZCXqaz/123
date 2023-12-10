package com.itheima.StudyTest01;

public class StudentTest {
    public static void main(String[] args) {
        Student s1=new Student();
        s1.setName("张三");
        s1.setAge(23);

        s1.study();
        s1.show();

        //
        Student s2=new Student();
        s2.setName("李四");
        s2.setAge(18);

        s2.study();
        s2.show();
    }
}
