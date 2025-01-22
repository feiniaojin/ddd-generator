package com.feiniaojin.ddd.generator.entity;

import lombok.Data;

@Data
public class ColumnEntity {
    //列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String columnComment;
    private String columnKey;
    //属性名称，首字母大写
    private String propertyNameFirstUppercase;
    //属性名称，首字母小写
    private String propertyNameFirstLowercase;
    //属性类型
    private String propertyType;
    //auto_increment
    private String extra;
}
