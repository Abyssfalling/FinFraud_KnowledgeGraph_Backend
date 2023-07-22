package com.example.fraud.sys.controller;

import com.example.fraud.sys.entity.Gender;
import com.example.fraud.sys.mapper.GenderMapper;
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
@RequestMapping("/sys/gender")
public class GenderController {
    @Autowired
    private GenderMapper genderMapper;
    @GetMapping
    public List<Gender> list3(){
        return genderMapper.selectAll();
    }
}
