package com.example.neo4jback.model;

public class Category {
    private int id;
    private String name;
    private ItemStyle itemStyle;

    // 省略构造函数、Getter和Setter

    public Category(int id, String name, ItemStyle itemStyle) {
        this.id = id;
        this.name = name;
        this.itemStyle = itemStyle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}
