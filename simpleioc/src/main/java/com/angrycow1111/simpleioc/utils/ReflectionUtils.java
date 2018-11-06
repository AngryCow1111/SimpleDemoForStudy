package com.angrycow1111.simpleioc.utils;

import java.lang.reflect.Field;

/**
 * @Author 万里独行侠
 * @Description //利用放射的原理完成对象的依赖注入
 * @Date 16:24 2018/10/13 0013
 * @Classname ReflectionUtils
 **/
public class ReflectionUtils {
    /**
     *
     * 功能描述: 对象进行依赖注入
     * @methodname:injectField
     * @param: [field, object, value]
     * @return: void
     * @author:
     * @date: 2018/10/13 0013 16:26
     */
    public static void injectField(Field field,Object object, Object value) throws IllegalAccessException {
        if (field != null) {
            // 设置访问
            field.setAccessible(true);
            // 对象进行属性注入
            field.set(object,value);
        }
    }
}
