package com.looksok.guava.basic;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

public class JoinerClassDemo {

    private List<String> listOfStrings = Lists.newArrayList("egg", "apple", null, "pineapple", "orange");
    private static final String SEPARATOR = ", ";

    public void runDemo() {
        System.out.println("\n=== Running Joiner class demo: Joining list of Strings ===");

        runJoinInJavaWay();
        runJoinInJavaWayWithLambdas();
        runJoinWithGuavaJoiner();
        runJoinWithGuavaJoinerNullSubstitution();
    }

    private void runJoinWithGuavaJoiner() {

        String result = Joiner.on(SEPARATOR).skipNulls().join(listOfStrings);

        System.out.println("\nGUAVA JOINER result:\n\t" + result);
    }

    private void runJoinWithGuavaJoinerNullSubstitution() {

        String result = Joiner.on(SEPARATOR).useForNull("EMPTY VALUE").join(listOfStrings);

        System.out.println("\nGUAVA JOINER with null substitution result:\n\t" + result);
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
