package com.angrycow1111.simpleioc.core;

import com.angrycow1111.simpleioc.bean.BeanDefinition;
import com.angrycow1111.simpleioc.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

/**
 * @Author 万里独行侠
 * @Description // 自定义容器类
 * @Date 18:02 2018/10/13 0013
 * @Classname JsonApplicationContext
 **/
public class JsonApplicationContext extends BeanFactoryImpl {
    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        loadFile();
    }

    /**
     * 功能描述: 加载文件
     *
     * @methodname:loadFile
     * @param: []
     * @return: void
     * @author:
     * @date: 2018/10/13 0013 18:10
     */
    private void loadFile() {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {
        });
        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            // 注册bean
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }
}
