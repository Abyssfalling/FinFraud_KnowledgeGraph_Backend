package com.example.neo4jback.model.categories;

public class Category2 {
    private int id;
    private String name;
    private String symbol;
    private ItemStyle2 itemStyle;


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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ItemStyle2 getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle2 itemStyle) {
        this.itemStyle = itemStyle;
    }

    // 如果需要，可以添加其他属性和方法

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", itemStyle=" + itemStyle +
                '}';
    }
}
