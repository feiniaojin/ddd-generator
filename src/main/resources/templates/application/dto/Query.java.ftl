package ${classPackage}.application.dto;

import java.io.Serializable;
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.util.Date;

import lombok.Data;

/**
 * 表名称：${tableName}
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器code-generator生成
 * github：https://github.com/feiniaojin/code-generator
 */
@Data
public class ${classNameFirstUppercase}Query implements Serializable {
<#list columns as column>
    private ${column.propertyType} ${column.propertyNameFirstLowercase};
</#list>
    private Integer page = 1;
    private Integer pageSize = 10;
}
