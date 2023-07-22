package com.example.neo4jback.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type="属于")
public class EntityRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Entity entity;

    @EndNode
    private Action action;

    @Property
    private String relation;

}
