package com.dhu.aml.dao;

import com.dhu.aml.entity.ActionNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query; // 确保使用正确的包路径
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends Neo4jRepository<ActionNode, Long> {

    @Query("MATCH (a:Action) WHERE a.name = $name RETURN a")
    List<ActionNode> findByName(@Param("name") String name);
}