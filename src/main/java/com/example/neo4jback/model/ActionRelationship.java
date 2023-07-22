package com.example.neo4jback.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type="LINKED_TO")
public class ActionRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Action action1;

    @EndNode
    private Action action2;

    @Property
    private String relation;

}
