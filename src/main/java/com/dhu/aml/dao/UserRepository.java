package com.dhu.aml.dao;

import com.dhu.aml.entity.User;
import com.dhu.aml.entity.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.annotation.Query; // 确保使用正确的包路径
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {

    @Query("MATCH (node:Entity) RETURN count(node) AS entityCount")
    int getEntityCount();

    @Query("MATCH (node:Action) RETURN count(node) AS actionCount")
    int getActionCount();

    @Query("MATCH (node:HyperAction) RETURN count(node) AS hyperactionCount")
    int getHyperActionCount();
}


