package ${classPackage}.application;

import ${classPackage}.domain.${classNameFirstUppercase}EntityFactory;
import ${classPackage}.domain.${classNameFirstUppercase}Entity;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
public class ${classNameFirstUppercase}EntityFactoryImpl implements ${classNameFirstUppercase}EntityFactory{

    @Override
    public ${classNameFirstUppercase}Entity newInstance() {
        ${classNameFirstUppercase}Entity entity = new ${classNameFirstUppercase}Entity();
        //todo 完成创建逻辑
        return entity;
    }
}
