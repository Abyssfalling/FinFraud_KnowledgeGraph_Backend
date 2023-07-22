package com.example.fraud.sys.service.impl;

import com.example.fraud.sys.entity.Job;
import com.example.fraud.sys.mapper.JobMapper;
import com.example.fraud.sys.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ling
 * @since 2023-07-21
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

}
