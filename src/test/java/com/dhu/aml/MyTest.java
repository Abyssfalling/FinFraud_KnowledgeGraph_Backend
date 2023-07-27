package com.dhu.aml;


import org.junit.Test;

import java.io.*;

public class MyTest {

    @Test
    public void test() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\work\\workspaces\\反洗钱及其可视化系统\\aml - 完善\\src\\test\\java\\com\\dhu\\aml\\requirements.txt"));
        String res = "";
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            res += line.replace(" ","==");
        }
        bufferedReader.close();

        System.out.println(res);
    }

    @Test
    public void test1(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\work\\workspaces\\反洗钱及其可视化系统\\aml - 完善\\src\\test\\java\\com\\dhu\\aml\\requirements.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\work\\workspaces\\反洗钱及其可视化系统\\aml - 完善\\src\\test\\java\\com\\dhu\\aml\\output.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String replacedLine = line.replaceAll("\\s+", "==");
                writer.write(replacedLine);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
