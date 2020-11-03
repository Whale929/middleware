package com.example.springbootdemo2.entity;/**
 * Created by Administrator on 2019/3/16.
 */


import java.io.Serializable;

/**
 * 用户个人信息实体
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/16 9:21
 **/

public class Person implements Serializable{

    private Integer id;
    private Integer age;
    private String name;
    private String userName;
    private String location;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocation() {
        return location;
    }

    public Person(Integer id, Integer age, String name, String userName, String location) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.userName = userName;
        this.location = location;
    }
}























