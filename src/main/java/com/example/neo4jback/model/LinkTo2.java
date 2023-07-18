package com.example.neo4jback.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@RelationshipEntity(type = "LINKED_TO")
@Data
@Builder
public class LinkTo2 {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private HyperAction hyperaction;

    @EndNode
    private Action action;
}