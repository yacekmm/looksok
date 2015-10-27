package com.looksok.guava.basic;

import com.google.common.base.Strings;

public class StringsDemo {
    public void runDemo() {

        print("\n--- Guava Strings class demo ---");

        print("Strings.padEnd: " + Strings.padEnd("foo", 6, 'x'));
        print("Strings.padStart: " + Strings.padStart("foo", 6, 'x'));
        print("Strings.nullToEmpty: '" + Strings.nullToEmpty(null) + "'");
        print("Strings.emptyToNull: " + Strings.emptyToNull(""));
        print("Strings.isNullOrEmpty: " + Strings.isNullOrEmpty(""));
    }

    private void print(String msg) {
        System.out.println(msg + "\n");
    }
}
