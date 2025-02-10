package ${classPackage}.infrastructure.persistence.mapper;

import ${classPackage}.infrastructure.persistence.data.${classNameFirstUppercase};

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;

/**
 * 表名称：${tableName}自动生成的Mapper
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器code-generator生成
 * github：https://github.com/feiniaojin/code-generator
 */
@Generated("generator")
public interface ${classNameFirstUppercase}MapperEx {

    /**
     * 分页查询：统计总数
     * @param paramMap
     * @return
     */
    int pageListTotal(Map<String, Object> paramMap);

    /**
     * 分页查询：分页获取数据
     * @param paramMap
     * @return
     */
    List<${classNameFirstUppercase}> pageList(Map<String, Object> paramMap);
}
