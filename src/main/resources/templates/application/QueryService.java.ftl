package ${classPackage}.application;

import ${classPackage}.application.dto.${classNameFirstUppercase}ViewAssembler;
import ${classPackage}.infrastructure.persistence.mapper.${classNameFirstUppercase}Mapper;
import ${classPackage}.infrastructure.persistence.mapper.${classNameFirstUppercase}MapperEx;
import ${classPackage}.infrastructure.persistence.jdbc.${classNameFirstUppercase}Repository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ${classNameFirstUppercase}类Service实现类
 * 表名称：${tableName}
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器code-generator生成
 * github：https://github.com/feiniaojin/code-generator
 */
@Service
@Slf4j
public class ${classNameFirstUppercase}EntityQueryService {

    @Resource
    private ${classNameFirstUppercase}Mapper ${classNameFirstLowercase}Mapper;

    @Resource
    private ${classNameFirstUppercase}MapperEx ${classNameFirstLowercase}MapperEx;

    @Resource
    private ${classNameFirstUppercase}Repository ${classNameFirstLowercase}Repository;

    @Resource
    private ${classNameFirstUppercase}ViewAssembler viewAssembler;

}
