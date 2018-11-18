package com.qunar.task2.dao;

import com.qunar.task2.domain.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/18 14:25
 */
@Mapper
public interface ArticleDao {
    String TABLE_NAME=" word_count ";
    String INSERT_FIELDS=" title, words, chinese_words, english_words, symbol, digit ";
    String SELECT_FIELDS=" id, "+INSERT_FIELDS;

    @Insert({" insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{title},#{words},#{chineseWords}," +
            "#{englishWords},#{symbol},#{digit})" })
    int addArticle(Article article);

    @Select({" select ", SELECT_FIELDS ," from "+ TABLE_NAME, " where id = #{id}"})
    Article getArticle(int id);

    @Select({" select  ", SELECT_FIELDS, " from ",TABLE_NAME, " order by id desc "})
    List<Article> selectArticles();

    @Delete({" delete from ", TABLE_NAME, " where id = #{id}"})
    int deleteById(int id);

}
