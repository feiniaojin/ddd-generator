package ${classPackage}.application.dto;

import ${classPackage}.infrastructure.data.${classNameFirstUppercase};
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * entity装配为view
 * 表名称：${tableName}
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器code-generator生成
 * github：https://github.com/feiniaojin/code-generator
 */
@Component
public class ${classNameFirstUppercase}ViewAssembler {

    public ${classNameFirstUppercase}View mapToView(${classNameFirstUppercase} entity) {
        ${classNameFirstUppercase}View ${classNameFirstLowercase}View = ${classNameFirstUppercase}ViewMapper.INSTANCE.mapToView(entity);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return ${classNameFirstLowercase}View;
    }

    public List<${classNameFirstUppercase}View> mapToViewList(List<${classNameFirstUppercase}> entityList) {
        List<${classNameFirstUppercase}View> viewList = ${classNameFirstUppercase}ViewMapper.INSTANCE.mapToViewList(entityList);
        //TODO 完成装配逻辑，例如枚举转化、数据截取等逻辑，避免将逻辑泄露到service中
        return viewList;
    }

    @Mapper(componentModel = "spring")
    public interface ${classNameFirstUppercase}ViewMapper {
        ${classNameFirstUppercase}ViewMapper INSTANCE = Mappers.getMapper(${classNameFirstUppercase}ViewMapper.class);

        ${classNameFirstUppercase}View mapToView(${classNameFirstUppercase} entity);

        List<${classNameFirstUppercase}View> mapToViewList(List<${classNameFirstUppercase}> entityList);
    }
}
