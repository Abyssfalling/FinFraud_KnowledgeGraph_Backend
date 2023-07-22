package com.example.fraud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ling
 * @since 2023-07-21
 */
@TableName("modepie")
public class Modepie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer value;

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
        return "Modepie{" +
            "value=" + value +
            ", name=" + name +
        "}";
    }
}
