package com.cn.liu.util.security.sm4;

public class User {
    public int id;
    public int age;
    public char sex;

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public User(int id, int age, char sex) {
        this.id = id;
        this.age = age;
        this.sex = sex;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    /**
     * <groupId>org.example</groupId>
     *     <artifactId>untitled</artifactId>
     *     <version>1.0-SNAPSHOT</version>
     */
}
