package com.qunar.task1.fifth;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/22 0:36
 */
public class CatOrder extends AbstractOrder {

    public CatOrder(String order) {
        this.order = order;
    }

    @Override
    void doOwnResponsibility() {
        String[] items = order.split("\\s+");
        File catFile = new File(rootPath + items[1]);
        if (catFile.exists()) {
            //System.out.println("cat: " + fileName + ": No such file or directory");
            try {
                List<String> catList = Files.readLines(catFile, Charsets.UTF_8);
                result.setListResult(catList);
                result.setIntResult(null);
                result.setStrResult(null);
            } catch (IOException e) {
                System.out.println("文件不存在");
            }
        }
    }
}
