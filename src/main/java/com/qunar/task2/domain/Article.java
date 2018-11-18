package com.qunar.task2.domain;

/**
 * @author zgm
 * @description
 * @date 2018/11/18 14:19
 */
public class Article {
    private int id;
    private String title;
    private int words;
    private int chineseWords;
    private int englishWords;
    private int symbol;
    private int digit;

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getChineseWords() {
        return chineseWords;
    }

    public void setChineseWords(int chineseWords) {
        this.chineseWords = chineseWords;
    }

    public int getEnglishWords() {
        return englishWords;
    }

    public void setEnglishWords(int englishWords) {
        this.englishWords = englishWords;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }
}
