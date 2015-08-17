package com.looksok.init;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){

        //traditional map initialization
        Map<String, Map<String, Integer>> lookupTraditional = new HashMap<String, Map<String, Integer>>();

        //Java 7 initialization with Diamond Operator
        Map<String, Map<String, Integer>> lookupJava7 = new HashMap<>();

        //Guava initialization
        Map<String, Map<String, Integer>> lookup = Maps.newHashMap();
    }
}
