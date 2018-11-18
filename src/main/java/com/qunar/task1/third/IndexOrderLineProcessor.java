package com.qunar.task1.third;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.LineProcessor;

import java.io.IOException;
import java.util.*;

/**
 * @author zgm
 * @description
 * @date 2018/11/13 17:19
 */
public class IndexOrderLineProcessor implements LineProcessor<Map<String, String>> {
    private final Splitter SPLITTER = Splitter.onPattern("\\s+");
    private Map<String, String> indexOrderMap = new HashMap<>();

    @Override
    public boolean processLine(String s) throws IOException {
        String key = Iterables.get(SPLITTER.split(s), 0);
        String value = Iterables.get(SPLITTER.split(s), 1);
        indexOrderMap.put(key, value);
        return true;
    }

    @Override
    public Map<String, String> getResult() {
        return indexOrderMap;
    }
}
