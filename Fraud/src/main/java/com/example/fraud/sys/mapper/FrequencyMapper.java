package com.example.fraud.sys.mapper;

import com.example.fraud.sys.entity.Frequency;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ling
 * @since 2023-07-20
 */
@Mapper
public interface FrequencyMapper extends BaseMapper<Frequency> {
    @Select("select * from Frequency")
    List<Frequency> selectAll();
}
