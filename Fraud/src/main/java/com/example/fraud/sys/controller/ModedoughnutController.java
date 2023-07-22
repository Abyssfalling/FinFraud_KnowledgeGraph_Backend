package com.example.fraud.sys.controller;

import com.example.fraud.sys.entity.Modedoughnut;
import com.example.fraud.sys.entity.Modepie;
import com.example.fraud.sys.mapper.ModedoughnutMapper;
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
@RequestMapping("/sys/modedoughnut")
public class ModedoughnutController {

    @Autowired
    private ModedoughnutMapper modedoughnutMapper;
    @GetMapping
    public List<Modedoughnut> list6(){
        return modedoughnutMapper.selectAll();
    }
}
