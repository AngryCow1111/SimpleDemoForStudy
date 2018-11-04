package com.google.guice.mapper;

public class UserDaoImpl implements UserDao {
    @Override
    public void getUserList() {
        System.out.println("获得用户列表！");
    }
}
