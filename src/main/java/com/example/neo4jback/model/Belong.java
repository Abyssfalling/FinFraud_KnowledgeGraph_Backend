package com.example.neo4jback.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@RelationshipEntity(type = "属于")
@Data
@Builder
public class Belong {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Action action;

    @EndNode
    private Entity entity;
}