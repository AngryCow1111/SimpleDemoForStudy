package com.google.guice.service;

import com.google.guice.service.impl.UserServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy( UserServiceImpl.class )
public interface UserService {

    void doSomething(Integer pagesize);
}
