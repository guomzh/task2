package com.qunar.task1.first;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author zgm
 * @description
 * @date 2018/11/8 14:48
 */
public class DiaryAnalysis {
    //comparator比较器，对http请求数量排序
    static class ValueComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    }

    public static void main(String[] args) throws IOException {
        //读取文件
        File file = new File("src\\main\\java\\com\\qunar\\task1\\first\\access.log");

        //1、计算总请求量
        ToListLineProcessor processor = new ToListLineProcessor();
        List<String> readLines = Files.readLines(file, Charsets.UTF_8, processor);
        System.out.println("总请求量为： " + readLines.size());

        //2、请求最频繁的10个 HTTP 接口，及其相应的请求数量
        Map<String, Integer> map = new HashMap<>(150000);
        for (String tmp : readLines) {
            Integer count = map.get(tmp);
            if (count != null) {
                map.put(tmp, count + 1);
            } else {
                map.put(tmp, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list, new ValueComparator());
        System.out.println("请求最频繁的10个HTTP接口为：");
        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i).getKey() + "    请求数量：" + list.get(i).getValue());
        }

        //3、POST 和 GET 请求量分别为多少
        List<String> lineItem = Files.readLines(file, Charsets.UTF_8);
        int getNums = 0, postNums = 0;
        for (String item : lineItem) {
            if (item.startsWith("GET")) {
                getNums++;
            } else if (item.startsWith("POST")) {
                postNums++;
            } else {
                System.out.println("我不是GET或POST请求：  " + item);
            }
        }
        System.out.println("POST请求数量为： " + postNums);
        System.out.println("GET请求数量为： " + getNums);

        //4、URI 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类，输出各个类别下 URI 都有哪些
        List<String> urls = new ArrayList<>();
        urls.addAll(map.keySet());
        Map<String, List<String>> typeOfUrlMap = new HashMap<>();
        for (String url : urls) {
            String key = url.split("/")[1];
            if (typeOfUrlMap.get(key) == null) {
                List<String> items = new ArrayList<>();
                items.add(url);
                typeOfUrlMap.put(key, items);
            } else {
                typeOfUrlMap.get(key).add(url);
            }
        }
        for (Map.Entry<String, List<String>> entry : typeOfUrlMap.entrySet()) {
            System.out.println("===================分割线============================");
            System.out.println("类别为'/" + entry.getKey() + "'的URI有以下这些:");
            List<String> showList = entry.getValue();
            for (String uri : showList) {
                System.out.println(uri);
            }
        }
    }

}
