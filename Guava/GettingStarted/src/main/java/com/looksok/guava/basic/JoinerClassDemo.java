package com.looksok.guava.basic;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class JoinerClassDemo {

    private static final String SEPARATOR = ", ";

    private List<String> listOfStrings = Lists.newArrayList("egg", "apple", null, "pineapple", "orange");

    public void runDemo() {
        System.out.println("\n=== Running Joiner class demo: Joining list of Strings ===");

        runJoinInJavaWay();
        runJoinInJavaWayWithLambdas();
        runJoinWithGuavaJoiner();
        runJoinWithGuavaJoinerNullSubstitution();
        runJoinWithGuavaJoinerPlusExtraElements();
        runGuavaJoinerOnMap();
    }


    private void runJoinWithGuavaJoiner() {

        String result = Joiner.on(SEPARATOR).skipNulls().join(listOfStrings);

        System.out.println("\nGUAVA JOINER result:\n\t" + result);
    }

    private void runJoinWithGuavaJoinerNullSubstitution() {

        String result = Joiner.on(SEPARATOR).useForNull("EMPTY VALUE").join(listOfStrings);

        System.out.println("\nGUAVA JOINER with null substitution result:\n\t" + result);
    }

    private void runJoinWithGuavaJoinerPlusExtraElements() {

        String result = Joiner.on(SEPARATOR).skipNulls().join(listOfStrings);

        StringBuilder builder = new StringBuilder(result);
        String extendedResult = Joiner.on(SEPARATOR).skipNulls().join(builder, "Strawberry");

        System.out.println("\nGUAVA JOINER result with extension:\n\t" + extendedResult);
    }

    private void runGuavaJoinerOnMap() {
        Map<String, Integer> map = buildStringIntegerMap();

        String mapJoinResult = Joiner.on(SEPARATOR + "\n\t").withKeyValueSeparator(" has length of: ").join(map);

        System.out.println("\nGUAVA JOINER on MAP result:\n\t" + mapJoinResult);
    }

    private Map<String, Integer> buildStringIntegerMap() {
        Map<String, Integer> map = Maps.newHashMap();
        listOfStrings.stream()
                .filter(item -> item != null)
                .forEach(item -> map.put(item, item.length()));
        return map;
    }

    private void runJoinInJavaWay() {
        StringBuilder builder = new StringBuilder();
        for (String item : listOfStrings){
            if(item != null) {
                builder.append(item).append(SEPARATOR);
            }
        }
        builder.setLength(builder.length() - SEPARATOR.length());

        System.out.println("\nJava way join result:\n\t" + builder.toString());
    }

    private void runJoinInJavaWayWithLambdas() {
        String result = listOfStrings.stream()
                .filter( item -> item != null)
                .reduce((s, s2) -> s + SEPARATOR + s2)
                .get();

        System.out.println("\nJava with lambda join result:\n\t" + result);
    }
}
