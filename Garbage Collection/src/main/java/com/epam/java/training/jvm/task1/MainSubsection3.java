package com.epam.java.training.jvm.task1;

import javassist.CannotCompileException;
import javassist.ClassPool;

import java.util.stream.Stream;

/**
 * Created by Vladislav Zhelezov.
 */
public class MainSubsection3 {

    //-XX:MaxMetaspaceSize=400m
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();
        //Overflow Metaspace
        Stream.iterate(1, x -> x + 1)
                .forEach(x -> {
                    try {
                        pool.makeClass("com.example.Kitty" + x).toClass();
                    } catch (CannotCompileException error) {
                        error.printStackTrace();
                    }
                });
    }
}
