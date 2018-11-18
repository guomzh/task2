package com.qunar.task1.first;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;

import java.io.IOException;
import java.util.List;

/**
 * @author zgm
 * @description
 * @date 2018/11/8 15:04
 */
public class ToListLineProcessor implements LineProcessor<List<String>> {
    private static final Splitter splitter = Splitter.on(" ");
    private List<String> items = Lists.newArrayList();
    private static final int index = 1;

    @Override
    public boolean processLine(String s) throws IOException {
        items.add(Iterables.get(splitter.split(s), index));
        return true;
    }

    @Override
    public List<String> getResult() {
        return items;
    }
}
