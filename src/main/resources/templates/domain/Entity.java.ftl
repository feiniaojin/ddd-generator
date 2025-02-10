package ${classPackage}.domain;

import com.feiniaojin.ddd.AbstractDomainMask;
import lombok.Data;
<#if createDateColumn??>
import org.springframework.data.annotation.CreatedDate;
</#if>
import org.springframework.data.annotation.Id;
<#if lastModifiedDateColumn??>
import org.springframework.data.annotation.LastModifiedDate;
</#if>
<#if versionColumn??>
import org.springframework.data.annotation.Version;
</#if>
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.util.Date;

/**
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
@Data
@Generated("generator")
public class ${classNameFirstUppercase}Entity extends AbstractDomainMask {
<#list columns as column>
     <#if column.propertyNameFirstLowercase == "id"
     || column.propertyNameFirstLowercase == "version"
     || column.propertyNameFirstLowercase == "createdBy"
     || column.propertyNameFirstLowercase == "createdDate"
     || column.propertyNameFirstLowercase == "lastModifedBy"
     || column.propertyNameFirstLowercase == "lastModifedDate">
     <#else>
     /**
     * ${column.columnComment}
     */
    private ${column.propertyType} ${column.propertyNameFirstLowercase};
     </#if>
</#list>
}
