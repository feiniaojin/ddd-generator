package ${classPackage}.infrastructure;

import ${classPackage}.domain.*;
import com.feiniaojin.ddd.EntityId;
import ${classPackage}.infrastructure.data.${classNameFirstUppercase};
import ${classPackage}.infrastructure.jdbc.${classNameFirstUppercase}Repository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
public class ${classNameFirstUppercase}EntityRepositoryImpl implements ${classNameFirstUppercase}EntityRepository {

@Resource
private ${classNameFirstUppercase}Repository jdbc${classNameFirstUppercase}Repository;

    @Override
    public ${classNameFirstUppercase}Entity load(EntityId entityId) {

        ${classNameFirstUppercase} data = jdbc${classNameFirstUppercase}Repository.findByBizId(entityId.getValue());

        if (Objects.isNull(data)) {
            return null;
        }

        ${classNameFirstUppercase}Entity entity = new ${classNameFirstUppercase}Entity();
        //todo 业务字段

        //元数据
        entity.setId(data.getId());
        entity.setCreatedBy(data.getCreatedBy());
        entity.setCreatedDate(data.getCreatedDate());
        entity.setLastModifiedBy(data.getLastModifiedBy());
        entity.setLastModifiedDate(data.getLastModifiedDate());
        entity.setDeleted(data.getDeleted());

        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(${classNameFirstUppercase}Entity entity) {

        ${classNameFirstUppercase} data = new ${classNameFirstUppercase}();

        //元数据
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedDate(entity.getCreatedDate());
        data.setLastModifiedBy(entity.getLastModifiedBy());
        data.setLastModifiedDate(entity.getLastModifiedDate());
        data.setVersion(entity.getVersion());
        data.setId(entity.getId());

        //todo 业务字段

        jdbc${classNameFirstUppercase}Repository.save(data);
    }
}

