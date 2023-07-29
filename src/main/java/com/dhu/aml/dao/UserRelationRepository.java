package com.dhu.aml.dao;

import com.dhu.aml.entity.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationRepository extends Neo4jRepository<UserRelation, Long> {

    @Query("MATCH (a:Action)-[r:LINKED_TO]->(b:Action) WHERE a.name = $actionName AND b.name = $superActionName RETURN r")
    List<UserRelation> findSuperActionRelation(@Param("actionName") String actionName,
                                               @Param("superActionName") String superActionName);
}
