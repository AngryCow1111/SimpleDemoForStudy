package com.angrycow1111.simpleioc.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author 万里独行侠
 * @Description // spring中IOC容器能够识别的数据结构
 * @Date 15:38 2018/10/13 0013
 * @Classname BeanDefinition
 **/

@Data
@ToString
public class BeanDefinition {
    // bean的名称
    private String name;
    // 类名
    private String className;
    // 实现的接口名称
    private String interfaceName;
    // 构造传入的参数列表
    private List<ContructorArg> contructorArgs;
    // 注入的属性列表
    private List<PropertyArg> propertyArgs;
}
