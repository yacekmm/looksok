package com.looksok.nullcheck;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Main {

    public static void main(String[] args){

        // --------- null-safe equality check ---------
        String nullString = null;
        String nameString = "Jack";

        Objects.equal(nullString, nameString);  //returns false
        nullString.equals(nameString);          //throws NPE
        // ---------


        // --------- use the default value if previous arguments were null ---------
        String login = "userLogin";
        String name = MoreObjects.firstNonNull(methodThatMayReturnNull(), login);

        //is the same as Java's:
        name = methodThatMayReturnNull();
        if(name == null)
            name = login;
        // ---------


        // --------- Argument check against null ---------
        String nullArgument = null;
        Preconditions.checkNotNull(nullArgument, "Argument was null!");   //throws NPE if argument is null
        // ---------
    }

    private static String methodThatMayReturnNull() {
        return null;
    }
}
