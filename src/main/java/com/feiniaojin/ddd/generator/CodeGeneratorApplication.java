package com.feiniaojin.ddd.generator;

import com.feiniaojin.ddd.generator.service.CodeGeneratorService;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * 请输入类描述
 *
 * @author feiniaojin
 */
@SpringBootApplication
@MapperScans({
        @MapperScan(basePackages = {
                "com.feiniaojin.ddd.generator.dao"
        })
})
public class CodeGeneratorApplication {

    @Resource
    private CodeGeneratorService codeGeneratorService;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(CodeGeneratorApplication.class, args);
        context.getBean(CodeGeneratorService.class).generate();
        context.stop();
    }

}

