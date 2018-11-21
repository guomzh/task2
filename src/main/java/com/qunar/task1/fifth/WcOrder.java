package com.qunar.task1.fifth;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/22 1:15
 */
public class WcOrder extends AbstractOrder {

    public WcOrder(String order) {
        this.order = order;
    }


    @Override
    void doOwnResponsibility() {
        String[] items = order.split("\\s+");
        if (items.length == 2) {
            wcWithArgAndList(items[1], result.getListResult());
        } else if (items.length == 3) {
            wcWithArgAndFilename(items[1], items[2]);
        } else {
            System.out.println("待扩展1");
        }
    }

    public void wcWithArgAndFilename(String arg, String fileName) {
        if (arg.equals("-l")) {
            try {
                File wcFile = new File(rootPath + fileName);
                List<String> lines = Files.readLines(wcFile, Charsets.UTF_8);
                result.setIntResult(lines.size());
                result.setStrResult(null);
                result.setListResult(null);
            } catch (IOException e) {
                System.out.println();
            }
        } else {
            System.out.println("待扩展2");
        }
    }

    public void wcWithArgAndList(String arg, List<String> list) {
        if (arg.equals("-l")) {
            result.setIntResult(list.size());
            result.setStrResult(null);
            result.setListResult(null);
        } else {
            System.out.println("待扩展3");
        }
    }

}
