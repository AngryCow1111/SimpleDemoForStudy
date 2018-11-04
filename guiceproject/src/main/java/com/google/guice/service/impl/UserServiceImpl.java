package com.google.guice.service.impl;

import com.google.guice.mapper.UserDao;
import com.google.guice.service.UserService;
import com.google.inject.Inject;
import com.google.inject.name.Named;


public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Named( "interceptor" )
    public void doSomething(Integer pagesize) {
        userDao.getUserList();
    }
}
