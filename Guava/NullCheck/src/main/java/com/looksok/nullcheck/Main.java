package com.looksok.nullcheck;

import com.google.common.base.Objects;

public class Main {

    public static void main(String[] args){

        //null-safe equality check
        String nullString = null;
        String name = "Jack";

        Objects.equal(nullString, name);


        //
    }
}
