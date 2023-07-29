package com.dhu.aml.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "HyperAction")
public class HyperActionNode {

    @Id
    @GeneratedValue
    private Long identity;

    @Property(name = "name")
    private String name;

    @Property(name = "category")
    private int category = 2;
}
