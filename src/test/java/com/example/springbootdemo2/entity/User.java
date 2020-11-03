package com.example.springbootdemo2.entity;

public class User {
    private Integer id;
    private String userName;
    private String name;

    //空构造器与所有字段构造器
    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Integer id, String userName, String name) {
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}





















