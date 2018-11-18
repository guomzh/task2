package com.qunar.task1.second;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/11 17:31
 */
public class LineCounts {
    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\java\\com\\qunar\\task1\\second\\StringUtils.txt");
        List<String> lines = Files.readLines(file, Charsets.UTF_8);
        int counts = 0;
        for (String line : lines) {
            String target = line.trim();
            if (target.equals("")) {
                continue;
            } else if (target.startsWith("//") || (target.startsWith("<!--") && target.endsWith("-->"))) {
                continue;
            }
            /**
             * 这是一个多行注释
             */
            else if (target.startsWith("/*") || target.startsWith("*")) {
                continue;
            } else {
                counts++;
            }
        }
        File toWriteFile = new File("src\\main\\java\\com\\qunar\\task1\\second\\validLineCount.txt");
        if (!toWriteFile.exists()) {
            toWriteFile.createNewFile();
        }
        Files.write(String.valueOf(counts), toWriteFile, Charsets.UTF_8);
        System.out.println("统计有效代码行数完毕！");
    }
}
