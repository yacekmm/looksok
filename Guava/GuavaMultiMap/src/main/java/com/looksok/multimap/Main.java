package com.looksok.multimap;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<String, List<Integer>> playerScoresMap = new HashMap<String, List<Integer>>();
    private static Multimap<String, Integer> playerScoresMultimap = HashMultimap.create();

    public static void main(String[] args){
        System.out.println("Multimap demo!");

        addToOldStyleMultimap("Bob", 10);
        addToOldStyleMultimap("Bob", 20);
        addToOldStyleMultimap("Bob", 30);
        addToOldStyleMultimap("Kate", 130);
        System.out.println("Old style multimap result: " + playerScoresMap);

        addToGuavaMultimap("Alan", 2);
        addToGuavaMultimap("Alan", 4);
        addToGuavaMultimap("Alan", 6);
        addToGuavaMultimap("Paul", 87);
        System.out.println("Guava multimap result: " + playerScoresMultimap);
    }

    private static void addToOldStyleMultimap(String playerName, int scoreToAdd) {
        List<Integer> scoresList = new ArrayList<Integer>();
        if(playerScoresMap.containsKey(playerName)){
            scoresList = playerScoresMap.get(playerName);
        }

        scoresList.add(scoreToAdd);
        playerScoresMap.put(playerName, scoresList);
    }

    private static void addToGuavaMultimap(String playerName, Integer scoreToAdd) {
        playerScoresMultimap.put(playerName, scoreToAdd);
    }
}
