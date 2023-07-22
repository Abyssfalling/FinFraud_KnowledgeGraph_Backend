package com.example.fraud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ling
 * @since 2023-07-20
 */
@TableName("gender")
public class Gender implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("value")
    private Integer value;
    @TableField("name")
    private String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gender{" +
            "value=" + value +
            ", name=" + name +
        "}";
    }
}
