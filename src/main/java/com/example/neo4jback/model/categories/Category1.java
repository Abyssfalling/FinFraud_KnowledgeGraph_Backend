package com.example.neo4jback.model.categories;

public class Category1 {
    private int id;
    private String name;
    private ItemStyle itemStyle;

    public Category1(int id, String name, ItemStyle itemStyle) {
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
