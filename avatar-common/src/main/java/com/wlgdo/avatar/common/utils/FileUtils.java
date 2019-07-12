package com.wlgdo.avatar.common.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * 开始解析文件，文件以行为单位，返回String List
     *
     * @param filePath
     * @throws Exception
     * @author:Ligang.Wang[163.com](http://www.wlgdo.com)
     */
    public static List<String> readFiles(String filePath) throws Exception {
        if (filePath == null || "".equals(filePath)) {
            return null;
        }
        String[] pathArray = filePath.split(",");
        List<String> logLists = new ArrayList();
        for (String path : pathArray) {
            //资源自动释放，依赖与实现接口AutoCloseable，否则没有效果
            try (
                    FileReader reader = new FileReader(path);
                    BufferedReader br = new BufferedReader(reader)
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    logLists.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("解析文件异常了", e);
            }
        }
        return logLists;
    }

    /**
     * @Description:
     * @Author: Ligang.Wang[wangligang@eglsgame.com]
     * @Date:
     */
    public static void writeFile() {

        try {
            File writeName = new File("d://relations.txt");
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("xxxxxxxxxxx1\r\n"); // \r\n即为换行
                out.write("ssssssssssss\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
