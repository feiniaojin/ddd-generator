package com.feiniaojin.ddd.generator.dao;

import com.feiniaojin.ddd.generator.entity.ColumnEntity;
import com.feiniaojin.ddd.generator.entity.TableEntity;

import java.util.List;

public interface MysqlMetaDao {

    TableEntity queryTableMeta(String tableName);

    List<ColumnEntity> queryTableColumns(String tableName);
}
