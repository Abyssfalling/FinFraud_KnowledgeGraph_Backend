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
 * @since 2023-07-21
 */
@TableName("job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("id")
    private Integer id;
    @TableField("value")
    private Integer value;
    @TableField("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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
        return "Job{" +
            "id=" + id +
            ", value=" + value +
            ", name=" + name +
        "}";
    }
}
