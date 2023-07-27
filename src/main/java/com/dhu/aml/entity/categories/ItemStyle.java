package com.dhu.aml.entity.categories;

public class ItemStyle {
    private String color;
    private String borderColor;


    public ItemStyle() {
    }

    public ItemStyle(String color, String borderColor) {
        this.color = color;
        this.borderColor = borderColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    // 如果需要，可以添加其他属性和方法

    @Override
    public String toString() {
        return "ItemStyle{" +
                "color='" + color + '\'' +
                ", borderColor='" + borderColor + '\'' +
                '}';
    }
}