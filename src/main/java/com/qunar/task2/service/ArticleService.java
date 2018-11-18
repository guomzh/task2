package com.qunar.task2.service;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.qunar.task2.dao.ArticleDao;
import com.qunar.task2.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zgm
 * @description
 * @date 2018/11/18 14:26
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public List<Article> getArticleList() {
        return articleDao.selectArticles();
    }

    public void countWords(File file) throws IOException {
        Article article = new Article();
        article.setTitle(file.getName());

        int chineseWords = 0;
        int englishWords = 0;
        int symbol = 0;
        int digit = 0;

        String chineseRegEx = "[\u4e00-\u9fa5]";
        String symbolRegEx = "[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]";


        List<String> articleList = Files.readLines(file, Charsets.UTF_8);
        for (String line : articleList) {
            Pattern ChinesePattern = Pattern.compile(chineseRegEx);
            Matcher ChineseMatcher = ChinesePattern.matcher(line);
            while (ChineseMatcher.find()) {
                chineseWords++;
            }
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    englishWords++;
                } else if (c >= '0' && c <= '9') {
                    digit++;
                }
            }

            Pattern symbolPattern = Pattern.compile(symbolRegEx);
            Matcher symbolMatcher = symbolPattern.matcher(line);
            while (symbolMatcher.find()) {
                symbol++;
            }
        }
        article.setWords(chineseWords+englishWords+symbol+digit);
        article.setChineseWords(chineseWords);
        article.setEnglishWords(englishWords);
        article.setSymbol(symbol);
        article.setDigit(digit);
        articleDao.addArticle(article);
    }

    public boolean deleteById(String path , int id){
        Article article = articleDao.getArticle(id);
        File file = new File(path,article.getTitle());
        try {
            if(file.exists()){
                file.delete();
            }
        } catch (Exception e){
            return false;
        }
        articleDao.deleteById(id);
        return true;
    }

}
