package com.example.fraud.sys.mapper;

import com.example.fraud.sys.entity.Job;
import com.example.fraud.sys.entity.Modepie;
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
 * @since 2023-07-21
 */
@Mapper
public interface ModepieMapper extends BaseMapper<Modepie> {
    @Select("select * from Modepie")
    List<Modepie> selectAll();
}
