package com.cn.liu.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liu
 */
@ExcelIgnoreUnannotated
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @ExcelProperty(value = {"用户信息", "用户id"})
    public int id;
    @ExcelProperty(value = {"用户信息", "用户姓名"})
    public String name;
    @ExcelProperty(value = {"用户信息", "用户年龄"})
    public int age;
    @ExcelProperty(value = {"用户信息", "用户性别"})
    public String sex;
    @ExcelProperty(value = {"用户信息", "用户卡号"})
    public String cardNo;

    public LocalDateTime expireTime;


    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public String pwd;

    public User(int id, String name, int age, String sex, String cardNo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.cardNo = cardNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", cardNo='" + cardNo + '\'' +
                '}';
    }


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User(int id, String name, int age, String sex, String cardNo, String pwd) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.cardNo = cardNo;
        this.pwd = pwd;
    }

    public User() {
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
