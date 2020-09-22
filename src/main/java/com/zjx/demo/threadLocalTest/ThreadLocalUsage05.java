package com.zjx.demo.threadLocalTest;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.threadLocalTest
 * @date:2020/6/11
 **/
public class ThreadLocalUsage05 {
    public static void main(String[] args) {
        Student student = init();
//        new NameService().getName(student);
//        new SexService().getSex(student);
//        new ScoreService().getScore(student);

        initThreadLcoal();
        new NameService().getName();
        new SexService().getSex();
        new ScoreService().getScore();
    }


    private static Student init() {
        Student student = new Student();
        student.name = "Lemon";
        student.sex = "female";
        student.score = "100";
        return student;
    }

    private static void initThreadLcoal() {
        Student student = new Student();
        student.name = "Lemon";
        student.sex = "female";
        student.score = "100";
        ThreadLocalProcessor.studentThreadLocal.set(student);
    }
}
class ThreadLocalProcessor {

    public static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<>();

}

class Student {

    /**
     * 姓名、性别、成绩
     */
    String name;
    String sex;
    String score;

}

class NameService {

    public void getName(Student student) {
        System.out.println(student.name);
    }

    public void getName() {
        System.out.println(ThreadLocalProcessor.studentThreadLocal.get().name);
    }

}

class SexService {

    public void getSex(Student student) {
        System.out.println(student.sex);
    }

    public void getSex() {
        System.out.println(ThreadLocalProcessor.studentThreadLocal.get().sex);
    }

}

class ScoreService {

    public void getScore(Student student) {
        System.out.println(student.score);
    }

    public void getScore() {
        System.out.println(ThreadLocalProcessor.studentThreadLocal.get().score);
    }

}