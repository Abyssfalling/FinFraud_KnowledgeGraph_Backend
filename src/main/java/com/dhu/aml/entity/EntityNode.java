package com.dhu.aml.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "Entity")
public class EntityNode {

    @Id
    @GeneratedValue
    private Long identity;

    @Property(name = "name")
    private String name;

    @Property(name = "category")
    private String category = "0";
}
