package com.dhu.aml.entity.categories;

public class Category {
    private int id;
    private String name;
    private String symbol;
    private ItemStyle itemStyle;

    public Category(int id, String name, String symbol, ItemStyle itemStyle) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
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
