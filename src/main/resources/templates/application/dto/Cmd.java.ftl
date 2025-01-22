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
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
@Data
public class ${classNameFirstUppercase}Cmd implements Serializable {
<#list columns as column>
    private ${column.propertyType} ${column.propertyNameFirstLowercase};
</#list>
}
