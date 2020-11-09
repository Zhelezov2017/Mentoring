package com.epam.java.training.jvm.task4.service;


import org.apache.log4j.Logger;
import org.objectweb.asm.ClassReader;

import java.io.*;
import java.util.Objects;


/**
 * Created by Vladislav Zhelezov.
 */
public class CustomClassLoader extends ClassLoader {

    private static final Logger log = Logger.getLogger(String.valueOf(CustomClassLoader.class));

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream classFromTarget = null;
        try {
            classFromTarget = new FileInputStream(new File(name + ".class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (classFromTarget == null) {
            return super.findClass(name);
        }
        byte[] byteArrayClass = loadClassData(classFromTarget);
        //Get class package from ClassReader (Very cool thing)
        String cr = new ClassReader(byteArrayClass).getClassName().replace("/", ".");
        return defineClass(cr, byteArrayClass, 0, byteArrayClass.length);
    }

    private byte[] loadClassData(InputStream inputStreamClass) {
        log.info("Read to class");
        ByteArrayOutputStream byteArrayOutputClass = new ByteArrayOutputStream();
        log.info("Write into byte");
        int count = 0;
        try {
            while ((count = Objects.requireNonNull(inputStreamClass).read()) != -1) {
                byteArrayOutputClass.write(count);
            }
        } catch (IOException e) {
            log.error("IOException when reading!");
            e.printStackTrace();
        }
        log.info("Convert into byte array");
        return byteArrayOutputClass.toByteArray();
    }
}
