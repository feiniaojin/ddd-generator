<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${classPackage}.infrastructure.persistence.mapper.${classNameFirstUppercase}MapperEx">

    <select id="pageListTotal" resultType="java.lang.Integer">
        SELECT COUNT(1)
        FROM ${tableName}
        <where>

        </where>
    </select>

    <select id="pageList" resultMap="${classPackage}.infrastructure.persistence.mapper.${classNameFirstUppercase}Mapper.BaseResultMap">
        SELECT
        <include refid="${classPackage}.infrastructure.persistence.mapper.${classNameFirstUppercase}Mapper.BaseColumn"/>
        FROM ${tableName}
        <where>

        </where>
        LIMIT ${r'#{offset}'},${r'#{limit}'}
    </select>
</mapper>
