package com.qunar.task1.third;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;

import java.io.IOException;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/14 10:26
 */
public class ToListLineProcessor implements LineProcessor<List<String>> {
    private Splitter splitter = Splitter.onPattern("\\s+");
    private List<String> list = Lists.newArrayList();

    @Override
    public boolean processLine(String s) throws IOException {
        list.add(Iterables.get(splitter.split(s), 1));
        return true;
    }

    @Override
    public List<String> getResult() {
        return list;
    }
}
