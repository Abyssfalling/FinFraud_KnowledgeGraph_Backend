package com.dhu.aml.dao;

import com.dhu.aml.entity.EntityNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query; // 确保使用正确的包路径
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends Neo4jRepository<EntityNode, Long> {

    @Query("MATCH (e:Entity) WHERE e.name = $name RETURN e")
    EntityNode findByName(@Param("name") String name);
}
