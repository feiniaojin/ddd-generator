package ${classPackage}.ui.web;

import ${classPackage}.application.${classNameFirstUppercase}EntityCommandService;
import ${classPackage}.application.${classNameFirstUppercase}EntityQueryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${classNameFirstUppercase}类Controller类
 * 表名称：${tableName}
 * 表注释：${comments}
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
@RestController
@RequestMapping("${controllerRequestPath}")
public class ${classNameFirstUppercase}Controller {
    @Resource
    private ${classNameFirstUppercase}EntityCommandService entityCommandService;

    @Resource
    private ${classNameFirstUppercase}EntityQueryService entityQueryService;
}
