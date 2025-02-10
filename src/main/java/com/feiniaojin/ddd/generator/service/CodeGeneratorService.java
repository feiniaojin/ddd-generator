package com.feiniaojin.ddd.generator.service;

import com.feiniaojin.ddd.generator.bean.GeneratorConfig;
import com.feiniaojin.ddd.generator.dao.MysqlMetaDao;
import com.feiniaojin.ddd.generator.entity.ColumnEntity;
import com.feiniaojin.ddd.generator.entity.TableEntity;
import com.google.gson.Gson;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 请输入类描述
 *
 * @author feiniaojin
 */
@Service
@Slf4j
public class CodeGeneratorService {
    @Resource
    private MysqlMetaDao mysqlMetaDao;
    @Resource
    private Configuration configuration;
    org.apache.commons.configuration.Configuration cfg;
    Gson gson = new Gson();

    public void generate() throws Exception {
        log.info("generator:生成代码:开始");
        GeneratorConfig generatorConfigBean = loadGeneratorJsonConfig();
        cfg = loadTypeMappingConfig();
        log.info(gson.toJson(generatorConfigBean));
        String outputDirectoryPath = this.getClass().getResource("/").getPath();
        String outPutPath =
                outputDirectoryPath.substring(0, outputDirectoryPath.indexOf("target")) +
                        "src/test/java/";
        if (StringUtils.isBlank(generatorConfigBean.getOutPutPath())) {
            generatorConfigBean.setOutPutPath(outPutPath);
        }
        List<TableEntity> entities = generatorConfigBean.getEntities();
        for (TableEntity entity : entities) {
            generatorCodeConTable(generatorConfigBean, entity);
        }
        log.info("generator:生成代码:完成");
    }

    private org.apache.commons.configuration.Configuration loadTypeMappingConfig() {
        try {
            return new PropertiesConfiguration("tm.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

    private void generatorCodeConTable(GeneratorConfig generatorConfigBean,
                                       TableEntity entity) throws Exception {
        TableEntity tableEntity = prepareTableEntity(generatorConfigBean, entity);
        log.info("tableEntity=[{}]", gson.toJson(tableEntity));

        //domain
        generate(tableEntity, "/domain/Entity.java.ftl", "/domain/", "Entity", "java");
        generate(tableEntity, "/domain/Factory.java.ftl", "/domain/", "EntityFactory", "java");
        generate(tableEntity, "/domain/Exceptions.java.ftl", "/domain/exceptions/", "Exceptions", "java");
        generate(tableEntity, "/domain/DomainRepository.java.ftl", "/domain/", "EntityRepository", "java");

        //application
        generateCommand(tableEntity);
        generateQuery(tableEntity);
        generateDtoView(tableEntity);
        generateQueryService(tableEntity);
        generateCommandService(tableEntity);
        generateFactoryImpl(tableEntity);
        generateViewAssembler(tableEntity);

        //infrastructure
        generate(tableEntity, "/infrastructure/Data.java.ftl", "/infrastructure/persistence/data/", "", "java");
//        generateData(tableEntity);
        generate(tableEntity, "/infrastructure/JdbcRepository.java.ftl", "/infrastructure/persistence/jdbc/", "Repository", "java");
//        generateJdbcRepository(tableEntity);
        generate(tableEntity, "/infrastructure/DomainRepositoryImpl.java.ftl", "/infrastructure/persistence/", "EntityRepositoryImpl", "java");
//        generateDomainRepositoryImpl(tableEntity);
        generate(tableEntity, "/infrastructure/Mapper.java.ftl", "/infrastructure/persistence/mapper/", "Mapper", "java");
//        generateJavaMapper(tableEntity);
        generate(tableEntity, "/infrastructure/Mapper.xml.ftl", "/infrastructure/persistence/mappers-xml/", "Mapper", "xml");
//        generateXmlMapper(tableEntity);
        generate(tableEntity, "/infrastructure/MapperEx.java.ftl", "/infrastructure/persistence/mapper/", "MapperEx", "java");
//        generateJavaMapperEx(tableEntity);
        generate(tableEntity, "/infrastructure/Mapper.xml.ftl", "/infrastructure/persistence/mappers-xml/", "MapperEx", "xml");
//        generateXmlMapperEX(tableEntity);

        //user interface
        generateController(tableEntity);
    }

    private void generate(TableEntity tableEntity, String templatePath, String relativePath, String suffix, String ext) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        relativePath + tableEntity.getClassNameFirstUppercase() + suffix + "." +
                        ext;
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateData(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/Data.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/data/" + tableEntity.getClassNameFirstUppercase() +
                        ".java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateFactoryImpl(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/FactoryImpl.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/" + tableEntity.getClassNameFirstUppercase() +
                        "EntityFactoryImpl.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateJavaMapperEx(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/MapperEx.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/mapper/" + tableEntity.getClassNameFirstUppercase() +
                        "MapperEx.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateXmlMapperEX(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/MapperEx.xml.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/xmapper/" + tableEntity.getClassNameFirstUppercase() +
                        "MapperEx.xml";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateViewAssembler(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/dto/ViewAssembler.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/dto/" + tableEntity.getClassNameFirstUppercase() +
                        "ViewAssembler.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateController(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/ui/Controller.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/ui/web/" + tableEntity.getClassNameFirstUppercase() +
                        "Controller.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateCommandService(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/CommandService.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/" + tableEntity.getClassNameFirstUppercase() +
                        "EntityCommandService.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateQueryService(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/QueryService.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/" + tableEntity.getClassNameFirstUppercase() +
                        "EntityQueryService.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateQuery(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/dto/Query.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/dto/" + tableEntity.getClassNameFirstUppercase() +
                        "Query.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateCommand(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/dto/Cmd.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/dto/" + tableEntity.getClassNameFirstUppercase() +
                        "Cmd.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateDtoView(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/application/dto/View.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/application/dto/" + tableEntity.getClassNameFirstUppercase() +
                        "View.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateJavaMapper(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/Mapper.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/mapper/" + tableEntity.getClassNameFirstUppercase() +
                        "Mapper.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }


    private void generateDomainRepositoryImpl(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/DomainRepositoryImpl.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/" + tableEntity.getClassNameFirstUppercase() +
                        "EntityRepositoryImpl.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateJdbcRepository(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/JdbcRepository.java.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/jdbc/" + tableEntity.getClassNameFirstUppercase() +
                        "Repository.java";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private void generateXmlMapper(TableEntity tableEntity) throws Exception {
        Template template = configuration.getTemplate("/infrastructure/Mapper.xml.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
        String fileName =
                tableEntity.getOutPutPath() + tableEntity.getClassPackage().replace(".", "/") +
                        "/infrastructure/xmapper/" + tableEntity.getClassNameFirstUppercase() +
                        "Mapper.xml";
        forceDeleteOldFile(fileName);
        FileUtils.write(new File(fileName), text, "UTF-8");
    }

    private TableEntity prepareTableEntity(GeneratorConfig generatorConfigBean,
                                           TableEntity tableEntity) {
        tableEntity.setClassPackage(generatorConfigBean.getBasePackage());
        tableEntity.setClassNameFirstUppercase(tableEntity.getEntityName());
        tableEntity.setClassNameFirstLowercase(
                (new StringBuilder()).append(Character.toLowerCase(tableEntity.getEntityName().charAt(0)))
                        .append(tableEntity.getEntityName().substring(1))
                        .toString());
        tableEntity.setControllerRequestPath(tableEntity.getControllerRequestPath());
        tableEntity.setOutPutPath(generatorConfigBean.getOutPutPath());
        //查询数据库获取表的元信息
        TableEntity tableInDb = mysqlMetaDao.queryTableMeta(tableEntity.getTableName());
        tableEntity.setComments(tableInDb.getComments());
        //查询列信息
        List<ColumnEntity> columnEntities = mysqlMetaDao.queryTableColumns(tableEntity.getTableName());
        columnEntities.stream().forEach(c -> {
            c.setPropertyType(cfg.getString(c.getDataType()));
            c.setPropertyNameFirstLowercase(columnToProperty(c.getColumnName()));
            if (c.getDataType().contains("decimal")) {
                tableEntity.setHasBigDecimal(true);
            }
        });
        tableEntity.setColumns(columnEntities);
        return tableEntity;
    }

    private GeneratorConfig loadGeneratorJsonConfig() {
        try {
            String outputDirectoryPath = this.getClass().getResource("/").getPath();
            String path = outputDirectoryPath + "generator.json";
            log.info("加载generator.json配置文件,path=[{}]", path);
            File file = new File(path);
            String configStr = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
            return gson.fromJson(configStr, GeneratorConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("加载generator.json配置文件失败");
        }
    }

    private void forceDeleteOldFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 修正格式，有下划线的列转成驼峰法
     *
     * @param columnName
     */
    private String columnToProperty(String columnName) {
        if (columnName.contains("_")) {
            String[] strings = columnName.split("_");
            StringBuilder builder = new StringBuilder("");
            for (String str : strings) {
                builder.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1));
            }
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
            return builder.toString();
        }
        return columnName;
    }
}
