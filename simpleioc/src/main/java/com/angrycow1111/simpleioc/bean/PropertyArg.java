package com.angrycow1111.simpleioc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 万里独行侠
 * @Description //注入属性数据类型
 * @Date 16:45 2018/10/13 0013
 * @Classname PropertyArg
 **/
@Data
@ToString
public class PropertyArg {
    // 属性名称
    private String name;
    // 属性值
    private String value;
    // 属性类型名称
    private String typeName;
}
