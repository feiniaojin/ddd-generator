package com.feiniaojin.ddd.generator.bean;

import com.feiniaojin.ddd.generator.entity.TableEntity;
import lombok.Data;

import java.util.List;

@Data
public class GeneratorConfig {
    private String basePackage;
    private String outPutPath;
    private List<TableEntity> entities;
}
