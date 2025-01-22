package ${classPackage}.infrastructure.mapper;

import ${classPackage}.infrastructure.data.${classNameFirstUppercase};
import org.apache.ibatis.annotations.Param;

import javax.annotation.processing.Generated;

/**
 * 表名称：${tableName}自动生成的Mapper
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器code-generator生成
 * github：https://github.com/feiniaojin/code-generator
 */
@Generated("generator")
public interface ${classNameFirstUppercase}Mapper {
    int insert(${classNameFirstUppercase} record);
    ${classNameFirstUppercase} findOneById(@Param("id")Long id);
}
