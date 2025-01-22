package com.feiniaojin.ddd.generator.entity;

import lombok.Data;

import java.util.List;

@Data
public class TableEntity {
    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 表的备注
     */
    private String comments;
    /**
     * table下的所有column
     */
    private List<ColumnEntity> columns;
    /**
     * 类包名
     */
    private String classPackage;
    /**
     * 类名，首字母大写
     */
    private String classNameFirstUppercase;
    /**
     * 类名，首字母小写
     */
    private String classNameFirstLowercase;
    /**
     * controller请求路径
     */
    private String controllerRequestPath;

    private String outPutPath;

    /**
     * 创建时间数据列名
     */
    private String createdDateColumn;

    /**
     * 创建时间数据列名
     */
    private String createDateColumn;

    /**
     * 最后修改时间列名
     */
    private String lastModifiedDateColumn;

    /**
     * 乐观锁版本号列名
     */
    private String versionColumn;

    /**
     * 是否有BigDecimal
     */
    private Boolean hasBigDecimal = false;
}
