package com.google.guice.interceptor;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MyInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        //ServletRequestAttributes attributes = (ServletRequestAttributes) s) RequestContextHolder.getRequestAtttAttributes();
        Object[] args = methodInvocation.getArguments();
        String classType = methodInvocation.getMethod().getDeclaringClass().getName();//全路径名
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = methodInvocation.getMethod().getName(); //获取方法名称

        //获取参数名称和值
        Map<String, Object> nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName, args);
        System.out.println("参数名和值:" + nameAndArgs.toString());
        //List requestParams =  = RequestParamsContextHolder.getRequestPartParamsLocal();


        // //redisCli.set("pa("pageSize",50); 这个值放在那个接口存储到redis ？

        //动态的获取PageSize
        Integer pageSize = 50;
        System.out.println(nameAndArgs);
        System.out.println(nameAndArgs.size());
        if (nameAndArgs != null && nameAndArgs.size() > 0) {

            Integer pagesize = (Integer) nameAndArgs.get("pagesize");
            if (pagesize <= pageSize && pagesize > 0) {
                //满足条件放行
                return methodInvocation.proceed();
            }
            //不满足就不调用方法
            return null;
        }
        //无参数放行
        return null;
    }

    //需用到javassit包
    private Map getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map map = new HashMap();
        ClassPool pool = ClassPool.getDefault();
        //ClassClassPath classPath = new ClassClassPath(th(this.getClass());());
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);//接口全路径
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            System.out.println("PaginationHelper attr is null ");
            return null;
        }
        // String[] paramNames = new String[ng[cm.getParameterTterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
        }
        return map;
    }
}
