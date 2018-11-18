package com.qunar.task1.third;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zgm
 * @description
 * @date 2018/11/13 17:01
 */
public class ArticleDecrypt {
    public static void main(String[] args) throws IOException {
        System.out.println("文本解密中，请等待......");
        File propFile = new File("src\\main\\java\\com\\qunar\\task1\\third\\sdxl_prop.txt");
        File templateFile = new File("src\\main\\java\\com\\qunar\\task1\\third\\sdxl_template.txt");

        List<String> natureOrder = Files.readLines(propFile, Charsets.UTF_8, new ToListLineProcessor());

        Map<String, String> indexOrderMap = Files.readLines(propFile, Charsets.UTF_8, new IndexOrderLineProcessor());

        List<String> charOrder = new ArrayList<>(natureOrder);
        Collections.sort(charOrder);

        List<String> charOrderDesc = new ArrayList<>(natureOrder);
        Collections.sort(charOrderDesc, String.CASE_INSENSITIVE_ORDER.reversed());

        List<String> templateList = Files.readLines(templateFile, Charsets.UTF_8);
        List<String> resultList = new ArrayList<>();

        for (String text : templateList) {
            String reg = "\\$[a-zA-z]+\\([0-9]+\\)";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(text);

            String newText = text;
            while (matcher.find()) {
                String subStr = text.substring(matcher.start(), matcher.end());
                String[] strings = subStr.split("\\(");
                String type = strings[0].substring(1);
                int num = Integer.valueOf(strings[1].substring(0, strings[1].length() - 1));
                switch (type) {
                    case "natureOrder":
                        newText = newText.replace(subStr, natureOrder.get(num));
                        break;
                    case "indexOrder":
                        newText = newText.replace(subStr, indexOrderMap.get(String.valueOf(num)));
                        break;
                    case "charOrder":
                        newText = newText.replace(subStr, charOrder.get(num));
                        break;
                    case "charOrderDESC":
                        newText = newText.replace(subStr, charOrderDesc.get(num));
                        break;
                    default:
                        break;
                }
            }
            resultList.add(newText);
        }
        File newFile = new File("src\\main\\java\\com\\qunar\\task1\\third\\sdxl.txt");
        newFile.delete();
        for (String line : resultList) {
            Files.append(line + "\n", newFile, Charsets.UTF_8);
        }
        System.out.println("文本解密结束！");
    }
}
