package com.example.fraud.sys.controller;

import com.example.fraud.sys.entity.Job;;
import com.example.fraud.sys.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ling
 * @since 2023-07-21
 */
@RestController
@RequestMapping("/sys/job")
public class JobController {
    @Autowired
    private JobMapper jobMapper;
    @GetMapping
    public List<Job> list4(){
        return jobMapper.selectAll();
    }
}
