package com.google.guice;

import com.google.guice.interceptor.MyInterceptor;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

public class MyAppModule implements Module {


    @Override
    public void configure(Binder binder) {
//        binder.bind(UserService.class).to(UserServiceImpl.class);
//        binder.bind(UserDao.class).to(UserDaoImpl.class);
        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Names.named("interceptor")), new MyInterceptor());
    }
}
