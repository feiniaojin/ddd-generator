package ${classPackage}.domain.exceptions;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * ${classNameFirstUppercase}类异常及异常码
 * 表名称：${tableName}
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
public class ${classNameFirstUppercase}Exceptions {
    @ExceptionMapper(code = "1404",msg = "找不到该资源")
    public static final class NotFoundException extends RuntimeException{}
}
