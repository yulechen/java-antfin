package com.antfin.question.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    private FileUtil(){}

    public static List<String> readLines(File file, String encoding) {
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader =null;
        InputStreamReader inputStreamReader=null;
        try {
            if (file.isFile() && file.exists()) {
                inputStreamReader= new InputStreamReader(
                        new FileInputStream(file), encoding);
                bufferedReader= new BufferedReader(inputStreamReader);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }finally {

            if(null !=bufferedReader ){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }


    public static List<File> listFiles(String dir){
        File dirFile = new File(dir);
        return Arrays.asList(dirFile.listFiles());
    }

}
