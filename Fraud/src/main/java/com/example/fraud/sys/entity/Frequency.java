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
@TableName("frequency")
public class Frequency implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("frequency")
    private String frequency;
    @TableField("maleAxis")
    private Integer maleAxis;
    @TableField("femaleAxis")
    private Integer femaleAxis;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public Integer getMaleAxis() {
        return maleAxis;
    }

    public void setMaleAxis(Integer maleAxis) {
        this.maleAxis = maleAxis;
    }
    public Integer getFemaleAxis() {
        return femaleAxis;
    }

    public void setFemaleAxis(Integer femaleAxis) {
        this.femaleAxis = femaleAxis;
    }

    @Override
    public String toString() {
        return "Frequency{" +
            "frequency=" + frequency +
            ", maleAxis=" + maleAxis +
            ", femaleAxis=" + femaleAxis +
        "}";
    }
}
