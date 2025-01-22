<?xml version="1.0" encoding="UTF-8"?>
<!--
  NOTICE:本文件由代码生成器code-generator生成
  github：https://github.com/feiniaojin/code-generator
-->
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${classPackage}.infrastructure.mapper.${classNameFirstUppercase}Mapper">
    <resultMap id="BaseResultMap" type="${classPackage}.infrastructure.data.${classNameFirstUppercase}">
    <#list columns as column>
        <result column="${column.columnName}" property="${column.propertyNameFirstLowercase}"/>
    </#list>
    </resultMap>
    <sql id="BaseColumn">
      <#list columns as column>
       <#if column_has_next>
        ${column.columnName},
       </#if>
       <#if !column_has_next>
        ${column.columnName}
       </#if>
      </#list>
    </sql>
    <insert id="insert" parameterType="${classPackage}.infrastructure.data.${classNameFirstUppercase}">
        insert into ${tableName} (
            <#list columns as column>
               <#if column_has_next>
        ${column.columnName},
               </#if>
               <#if !column_has_next>
        ${column.columnName}
               </#if>
            </#list>
        )
        <trim prefix="values (" suffix=")">
            <#list columns as column>
                   <#if column_has_next>
            ${'#{'+column.propertyNameFirstLowercase+'}'},
                   </#if>
                   <#if !column_has_next>
            ${'#{'+column.propertyNameFirstLowercase+'}'}
                   </#if>
            </#list>
        </trim>
    </insert>
    <select id="findOneById" resultMap="BaseResultMap">
        select
        <include refid="BaseColumn"/>
        from ${tableName} where id=${r'#{id}'}
    </select>
</mapper>
