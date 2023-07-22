package com.example.fraud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ling
 * @since 2023-07-20
 */
@TableName("age")
public class Age implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("xAxis")
    private String xAxis;
    @TableField("yAxis")
    private Integer yAxis;

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }
    public Integer getyAxis() {
        return yAxis;
    }

    public void setyAxis(Integer yAxis) {
        this.yAxis = yAxis;
    }


    @Override
    public String toString() {
        return "Age{" +
            "xAxis=" + xAxis +
            ", yAxis=" + yAxis +
        "}";
    }
}
