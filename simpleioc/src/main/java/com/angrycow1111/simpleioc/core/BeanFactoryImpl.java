package com.angrycow1111.simpleioc.core;

import com.angrycow1111.simpleioc.bean.BeanDefinition;
import com.angrycow1111.simpleioc.bean.ContructorArg;
import com.angrycow1111.simpleioc.utils.BeanUtils;
import com.angrycow1111.simpleioc.utils.ClassUtils;
import com.angrycow1111.simpleioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 万里独行侠
 * @Description //bean工厂的实现类
 * @Date 15:52 2018/10/13 0013
 * @Classname BeanFactoryImpl
 **/
public class BeanFactoryImpl implements BeanFactory {

    private static final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap();
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();
    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    /**
     * 功能描述: 通过beanName获得bean实例
     *
     * @methodname:getBean
     * @param: [name]
     * @return: java.lang.Object
     * @author:
     * @date: 2018/10/13 0013 15:52
     */
    public Object getBean(String name) throws Exception {
        Object bean = beanMap.get(name);
        // 判断是否已经实例化过
        if (bean != null) {
            // 已经实例化
            return bean;
        }
        bean = createBean(beanDefinitionMap.get(name));
        if (bean != null) {
            // 注入属性
            populateBean(bean);

            // 将bean放到beanMap中进行管理
            beanMap.put(name, bean);
        }

        return bean;
    }

    // 在IOC容器中注册bean
    public void registerBean(String name, BeanDefinition beanDefinition) {

        beanDefinitionMap.put(name, beanDefinition);
        beanNameSet.add(name);
    }

    /**
     * 功能描述: 创建bean
     *
     * @methodname:createBean
     * @param: [beanDefinition]
     * @return: java.lang.Object
     * @author:
     * @date: 2018/10/13 0013 17:10
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        // 获得beanName
        String className = beanDefinition.getClassName();

        // 根据beanName获得class对象
        Class clazz = ClassUtils.loadClass(className);
        // 判断clazz是否为空
        if (clazz == null) {
            throw new Exception("can not find bean by " + className);
        }
        List<ContructorArg> contructorArgs = beanDefinition.getContructorArgs();
        // 判断构造参数是否为空
        if (contructorArgs != null && contructorArgs.size() > 0) {

            List<Object> args = new ArrayList<Object>();

            // 不为空
            for (ContructorArg contructorArg : contructorArgs) {
                if (contructorArg.getValue() != null) {
                    args.add(contructorArg.getValue());
                } else {
                    args.add(contructorArg.getRef());
                }
            }
            return BeanUtils.instanceByCgLib(clazz, clazz.getConstructor(), args.toArray());
//            args.stream().map(it -> it.getClass()).collection(Collections.toList()).toArray(new Class[]{});
        }
        // 构造传入参数为空
        return BeanUtils.instanceByCgLib(clazz, null, null);


    }

    /**
     * 功能描述: 对象中注入属性
     *
     * @methodname:populateBean
     * @param: [bean]
     * @return: void
     * @author:
     * @date: 2018/10/13 0013 17:33
     */
    public void populateBean(Object bean) throws Exception {
        // 获得类的属性列表
        Field[] declaredFields = bean.getClass().getSuperclass().getDeclaredFields();

        if (declaredFields != null && declaredFields.length > 0) {
            for (Field declaredField : declaredFields) {
                String fieldName = declaredField.getName();

                if (beanNameSet.contains(declaredField.getName())) {
                    fieldName = StringUtils.uncapitalize(fieldName);
                    Object fieldBean = getBean(fieldName);
                    if (bean != null) {
                        ReflectionUtils.injectField(declaredField, bean, fieldBean);
                    }
                }

            }
        }

    }
}
