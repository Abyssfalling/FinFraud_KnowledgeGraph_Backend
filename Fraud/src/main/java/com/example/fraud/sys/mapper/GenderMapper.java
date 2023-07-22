package com.example.fraud.sys.mapper;

import com.example.fraud.sys.entity.Gender;
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
public interface GenderMapper extends BaseMapper<Gender> {
    @Select("select * from Gender")
    List<Gender> selectAll();
}
