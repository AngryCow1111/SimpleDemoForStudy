package com.angrycow1111.simpleioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Author 万里独行侠
 * @Description //实例化工具类
 * @Date 16:14 2018/10/13 0013
 * @Classname BeanUtils
 **/
public class BeanUtils {
    /**
     *
     * 功能描述: 实例化bean
     * @methodname:instanceByCgLib
     * @param: [clazz, constructor, args]
     * @return: T
     * @author:
     * @date: 2018/10/13 0013 16:23+
     */
    public static <T> T instanceByCgLib(Class<T> clazz, Constructor constructor, Object[]args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(NoOp.INSTANCE);
        // 判断构造器是否为空
        if (constructor == null) {
            return (T) enhancer.create();
        }
        // 构造器不为空
        return (T) enhancer.create(constructor.getParameterTypes(),args);
    }
}
