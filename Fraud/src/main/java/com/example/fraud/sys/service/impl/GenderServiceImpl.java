package com.example.fraud.sys.service.impl;

import com.example.fraud.sys.entity.Gender;
import com.example.fraud.sys.mapper.GenderMapper;
import com.example.fraud.sys.service.IGenderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ling
 * @since 2023-07-20
 */
@Service
public class GenderServiceImpl extends ServiceImpl<GenderMapper, Gender> implements IGenderService {

}
