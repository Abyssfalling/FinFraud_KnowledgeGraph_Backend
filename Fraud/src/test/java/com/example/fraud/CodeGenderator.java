package com.example.fraud;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenderator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/fraud?characterEncoding=UTF-8", "root", "Xjw2039225151")
                .globalConfig(builder -> {
                    builder.author("ling") // 设置作者
//                            .enableSwagger()
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\fraud\\Fraud\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.fraud") // 设置父包名
                            .moduleName("sys") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\fraud\\Fraud\\src\\main\\resources\\mapper\\sys")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("Age","Frequency","Gender","Job","ModePie","ModeDoughnut") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
