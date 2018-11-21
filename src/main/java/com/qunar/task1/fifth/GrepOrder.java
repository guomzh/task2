package com.qunar.task1.fifth;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/22 0:56
 */
public class GrepOrder extends AbstractOrder {

    public GrepOrder(String order) {
        this.order = order;
    }

    @Override
    void doOwnResponsibility() {
        String[] items = order.split("\\s+");
        if (items.length == 2) {
            grepWithKeywordAndList(items[1], result.getListResult());
        } else if (items.length == 3) {
            grepWithKeywordAndFileName(items[1], items[2]);
        } else {
            System.out.println("更多grep命令在在此处扩展");
        }
    }

    void grepWithKeywordAndFileName(String keyword, String fileName) {
        File grepFile = new File(rootPath + fileName);
        if (grepFile.exists()) {
            try {
                List<String> allLines = Files.readLines(grepFile, Charsets.UTF_8);
                List<String> listResult = new ArrayList<>();
                for (String str : allLines) {
                    if (str.contains(keyword)) {
                        listResult.add(str);
                    }
                }
                result.setListResult(listResult);
                result.setStrResult(null);
                result.setIntResult(null);
            } catch (IOException e) {
                System.out.println("io异常");
            }
        }
    }

    void grepWithKeywordAndList(String keyword, List<String> list) {
        if(list==null){
            System.out.println("输入linux命令有语法错误");
            return;
        }
        List<String> listResult = new ArrayList<>();
        for (String str : list) {
            if (str.contains(keyword)) {
                listResult.add(str);
            }
        }
        result.setListResult(listResult);
        result.setStrResult(null);
        result.setIntResult(null);
    }

}
