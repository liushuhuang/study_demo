package com.cn.liu.entity.test;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author liushuhuang
 * @date 2023/2/10
 */
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String cardNo, int age, String sex, String id) {
        this.name = name;
        this.cardNo = cardNo;
        this.age = age;
        this.sex = sex;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public User() {
    }

    @ExcelProperty(value = "姓名",index = 0)
    public String name;
    @ExcelProperty(value = "身份证号",index = 1)
    public String cardNo;
    @ExcelProperty(value = "年龄",index = 2)
    public int age;
    @ExcelProperty(value = "性别",index = 3)
    public String sex;
    @ExcelProperty(value = "用户id",index = 4)
    public String id;
}
