package com.epam.java.training.jvm.task1;

/**
 * Created by Vladislav Zhelezov.
 */
public class MainSubsection4 {

    public static void main(String[] args) {
        //Recursion
        MainSubsection4 main = new MainSubsection4();

        main.methodRecursion("I like world");
    }

    private void methodRecursion(String string) {
        methodRecursion(string);

        System.out.println(string);
    }
}
