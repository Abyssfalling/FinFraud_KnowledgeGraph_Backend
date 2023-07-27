package com.dhu.aml.utils;

import org.springframework.util.AntPathMatcher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileOperation {



    //读取行记录
    public static List<String> readRows(String path){

        List<String> rows = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(path);) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    //随机生成一个字符串
    public static String randomNum(int len){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int num = random.nextInt(10);
            sb.append(num);
        }
        String str = sb.toString();
        return str;
    }

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    public static boolean check(String []urls,String requestUrl){
        for (String url : urls) {
            if(PATH_MATCHER.match(url,requestUrl))
                return true;
        }
        return false;
    }

}
