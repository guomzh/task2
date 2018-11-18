package com.qunar.task1.fifth;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zgm
 * @description
 * @date 2018/11/14 17:01
 */
public class LinuxShell {
    String rootPath = "src\\main\\java\\com\\qunar\\task1\\fifth\\";

    public static void main(String[] args) throws IOException {
        LinuxShell shell = new LinuxShell();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] orders = input.split("\\|");
            if (orders.length == 1) {
                shell.process(orders[0]);
            } else {
                int len = orders.length;
                String str = orders[0].trim();
                String[] items = str.split("\\s+");
                String type = items[0];
                int wc = 0;
                List<String> pipeIn;
                if (type.equals("cat")) {
                    pipeIn = shell.cat(items[1]);
                } else if (type.equals("grep")) {
                    pipeIn = shell.grep(items[1], items[2]);
                } else {
                    System.out.println("Linux语法错误。。。");
                    continue;
                }
                for (int i = 1; i < len; i++) {
                    str = orders[i].trim();
                    items = str.split("\\s+");
                    type = items[0];
                    if (type.equals("grep")) {
                        pipeIn = shell.grep(items[1], pipeIn);
                    } else if (type.equals("wc")) {
                        int result = shell.wc("-l", pipeIn);
                        System.out.println(result);
                        wc = 1;
                    }
                }
                if (wc == 1)
                    continue;
                for (String line : pipeIn) {
                    System.out.println(line);
                }
            }
        }
    }

    public void process(String order) throws IOException {
        String str = order.trim();
        String[] items = str.split("\\s+");
        String type = items[0];
        switch (type) {
            case "cat":
                List<String> catList = cat(items[1]);
                for (String line : catList) {
                    System.out.println(line);
                }
                break;
            case "grep":
                List<String> grepList = grep(items[1], items[2]);
                for (String line : grepList) {
                    System.out.println(line);
                }
                break;
            case "wc":
                int result = wc(items[1], items[2]);
                System.out.println(result);
                break;
            default:
                break;
        }
    }

    public List<String> grep(String word, String fileName) throws IOException {
        File grepFile = new File(rootPath + fileName);
        if (!grepFile.exists()) {
            return new ArrayList<>();
        }
        List<String> allLines = Files.readLines(grepFile, Charsets.UTF_8);
        List<String> result = new ArrayList<>();
        for (String str : allLines) {
            if (str.contains(word)) {
                result.add(str);
            }
        }
        return result;
    }

    public List<String> grep(String word, List<String> list) {
        List<String> result = new ArrayList<>();
        for (String str : list) {
            if (str.contains(word)) {
                result.add(str);
            }
        }
        return result;
    }

    public List<String> cat(String fileName) throws IOException {
        File catFile = new File(rootPath + fileName);
        if (!catFile.exists()) {
            //System.out.println("cat: " + fileName + ": No such file or directory");
            return new ArrayList<>();
        }
        List<String> catList = Files.readLines(catFile, Charsets.UTF_8);
        return catList;
    }

    public int wc(String arg, String fileName) throws IOException {
        File wcFile = new File(rootPath + fileName);
        if (!wcFile.exists()) {
            return -2;
        }
        if (arg.equals("-l")) {
            List<String> lines = Files.readLines(wcFile, Charsets.UTF_8);
            return lines.size();
        }
        return -1;
    }

    public int wc(String arg, List<String> list) {
        if (arg.equals("-l")) {
            return list.size();
        }
        return -1;
    }
}
