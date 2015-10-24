package com.looksok.guava.basic;

import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SplitterDemo {

    public void runDemo() {
        String csvRow = "desk,,chair,table,couch,";
        String mapString = "office=desk,kitchen=table,living room=couch";


        runJavaDemo(csvRow);
        runGuavaEagerDemo(csvRow);
        runGuavaLazyDemo(csvRow);
        runMapSplitterDemo(mapString);
    }

    private void runJavaDemo(String stringToSplit) {

        String[] result = stringToSplit.split(",");

        System.out.println("\nString.split() result:\n" + Arrays.toString(result));
    }

    private void runGuavaEagerDemo(String stringToSplit) {

        List<String> result = Splitter.on(",").splitToList(stringToSplit);

        System.out.println("\nGuava Eager Splitter result:\n" + result);
    }

    private void runGuavaLazyDemo(String stringToSplit) {
        Iterable result = Splitter.on(",").split(stringToSplit);

        System.out.println("\nGuava Splitter result:\n");
        result.forEach(System.out::println);
    }

    private void runMapSplitterDemo(String mapString) {
        Splitter.MapSplitter mapSplitter = Splitter.on(",").withKeyValueSeparator("=");
        Map<String, String> result = mapSplitter.split(mapString);

        System.out.println("\nGuava MapSplitter result:\n" + result);
    }
}
