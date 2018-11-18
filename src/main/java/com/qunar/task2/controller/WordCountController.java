package com.qunar.task2.controller;

import com.qunar.task2.domain.Article;
import com.qunar.task2.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/17 23:50
 */
@Controller
public class WordCountController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @RequestMapping(value = "/count")
    public String wordCount(HttpServletRequest request,
                            @RequestParam MultipartFile article,
                            Model model) throws IOException {
        if (!article.isEmpty()) {
            String path = request.getServletContext().getRealPath("/upload/");
            System.out.println(path);
            String filename = article.getOriginalFilename();
            File file = new File(path ,filename);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            article.transferTo(file);
            articleService.countWords(file);
            List<Article> articleList = articleService.getArticleList();
            model.addAttribute("articleList", articleList);
            return "index";
        } else {
            return "error";
        }
    }

    @RequestMapping(path = {"/delete"})
    public String deleteOneRecord(@RequestParam("id")int id,
                                  HttpServletRequest request,
                                  Model model){
        String path = request.getServletContext().getRealPath("/upload/");
        boolean flag = articleService.deleteById(path,id);
        if(flag){
            model.addAttribute("deleteTip", "删除成功!");
        }else {
            model.addAttribute("deleteTip", "删除出现异常");
        }
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

}
