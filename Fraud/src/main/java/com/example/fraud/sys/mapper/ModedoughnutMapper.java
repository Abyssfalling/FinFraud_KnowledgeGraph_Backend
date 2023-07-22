package com.example.fraud.sys.mapper;

import com.example.fraud.sys.entity.Modedoughnut;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fraud.sys.entity.Modepie;
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
public interface ModedoughnutMapper extends BaseMapper<Modedoughnut> {

    @Select("select * from Modedoughnut")
    List<Modedoughnut> selectAll();
}
