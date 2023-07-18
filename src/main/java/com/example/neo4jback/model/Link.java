package com.example.neo4jback.model;

public class Link {
    private int id;
    private String source;
    private String target;

    public Link() {
    }

    public Link(int id, String source, String target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    // 省略构造函数、Getter和Setter
}
