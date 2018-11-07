package com.google.guice;

import com.google.guice.interceptor.MyInterceptor;
import com.google.guice.service.UserService;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import org.junit.BeforeClass;

public class Test {
    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Names.named("interceptor")), new MyInterceptor());
            }
        });
    }

    @org.junit.Test
    public void testApp() {
        UserService userService = injector.getInstance(UserService.class);
        userService.doSomething(51);
    }
}
