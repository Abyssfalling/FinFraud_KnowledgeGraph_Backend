package com.example.fraud.sys.controller;

import com.example.fraud.sys.entity.Age;
import com.example.fraud.sys.mapper.AgeMapper;
import com.example.fraud.sys.mapper.FrequencyMapper;
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
 * @since 2023-07-20
 */
@RestController
@RequestMapping("/sys/age")
public class AgeController {
    @Autowired
    private AgeMapper ageMapper;
    @GetMapping
    public List<Age> list1(){
        return ageMapper.selectAll();
    }
}