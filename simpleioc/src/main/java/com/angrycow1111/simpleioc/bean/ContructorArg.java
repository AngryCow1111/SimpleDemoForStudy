package com.angrycow1111.simpleioc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 万里独行侠
 * @Description //构造参数数据类型
 * @Date 16:43 2018/10/13 0013
 * @Classname ContructorArg
 **/
@Data
@ToString
public class ContructorArg {
    // 构造参数的索引
    private int index;
    // 构造参数的引用
    private String ref;
    // 构造参数名称
    private String name;
    // 构造参数值
    private String value;
}
