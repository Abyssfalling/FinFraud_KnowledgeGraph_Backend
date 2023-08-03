package com.dhu.aml.dao;

import com.dhu.aml.entity.UserNode;
import com.dhu.aml.entity.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationRepository extends Neo4jRepository<UserRelation, Long> {

    @Query("MATCH (source:UserNode)-[relation:LINKED_TO]->(target:UserNode) " +
            "WHERE ID(source) = $nodeId OR ID(target) = $nodeId " +
            "RETURN relation")
    List<UserRelation> findBySourceNodeOrTargetNode(Long nodeId);
}
