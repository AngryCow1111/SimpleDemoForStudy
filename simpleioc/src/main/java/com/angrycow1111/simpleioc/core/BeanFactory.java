package com.angrycow1111.simpleioc.core;

/**
 * @Author 万里独行侠
 * @Description //对象工厂接口
 * @Date 15:40 2018/10/13 0013
 * @Classname BeanFactory
 **/
public interface BeanFactory {
    /**
     * 功能描述: 通过beanName获得相应的bean
     *
     * @methodname:getBean
     * @param: [name]
     * @return: java.lang.Object
     * @author:
     * @date: 2018/10/13 0013 15:51
     */
    Object getBean(String name) throws Exception;
}
