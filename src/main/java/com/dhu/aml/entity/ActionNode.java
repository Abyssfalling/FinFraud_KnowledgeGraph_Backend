package com.dhu.aml.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@NodeEntity(label = "Action")
public class ActionNode {

    @Id
    @GeneratedValue
    private Long identity;

    @Property(name = "name")
    private String name;

    @Property(name = "category")
    private String category = "1";
}

