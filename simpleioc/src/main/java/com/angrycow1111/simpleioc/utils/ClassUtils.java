package com.angrycow1111.simpleioc.utils;
/**
 * @Author 万里独行侠
 * @Description // 类相关的工具方法
 * @Date 16:02 2018/10/13 0013
 * @Classname ClassUtils
 **/
public class ClassUtils {
    /**
     *
     * 功能描述: 获得当前线程的默认类加载器
     * @methodname:getDefaultClassLoader
     * @param: []
     * @return: java.lang.ClassLoader
     * @author:
     * @date: 2018/10/13 0013 16:03
     */
    public static ClassLoader getDefaultClassLoader() {

        return Thread.currentThread().getContextClassLoader();
    }
    /**
     *
     * 功能描述: 使用类加载器通过类名进行类的加载获得相应的类对象
     * @methodname:loadClass
     * @param: [className]
     * @return: java.lang.Class
     * @author:
     * @date: 2018/10/13 0013 16:12
     */
    public static Class loadClass(String className) {
        try {

            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
