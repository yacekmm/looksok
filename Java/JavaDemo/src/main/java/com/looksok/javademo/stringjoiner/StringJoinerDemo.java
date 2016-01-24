package com.looksok.javademo.stringjoiner;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StringJoinerDemo {
    public void run() {
        basicUsage();
        basicUsageChained();
        joinArray();
        joinInline();
        joiningCollector();
    }

    private void basicUsage() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add("apple");
        joiner.add("banana");
        joiner.add("orange");

        System.out.println("Joiner result is: " + joiner.toString());
    }

    private void basicUsageChained() {
        StringJoiner joiner = new StringJoiner(",")
            .add("apple")
            .add("banana")
            .add("orange");

        System.out.println("Chained joiner result is: " + joiner.toString());
    }

    private void joinArray(){
        List<String> list = Arrays.asList("apple", "banana", "orange");
        String joined = String.join(", ", list);

        System.out.println("Join Array result is: " + joined);
    }

    private void joinInline(){
        String joined = String.join(", ", "apple", "banana", "orange");
        System.out.println("Join inline with varargs result is: " + joined);
    }

    private void joiningCollector(){
        List<String> list = Arrays.asList("apple", "banana", "orange");
        String joined = list.stream()
                .collect(Collectors.joining(", "));

        System.out.println("Joined with collector: " + joined);
    }
}
