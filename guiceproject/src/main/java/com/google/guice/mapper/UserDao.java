package com.google.guice.mapper;

import com.google.inject.ImplementedBy;

@ImplementedBy( UserDaoImpl.class )
public interface UserDao {

    void getUserList();
}
