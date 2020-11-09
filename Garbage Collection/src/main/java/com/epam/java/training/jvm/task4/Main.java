package com.epam.java.training.jvm.task4;

import com.epam.java.training.jvm.task4.interfaceRealize.Animal;
import com.epam.java.training.jvm.task4.service.CustomClassLoader;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * Created by Vladislav Zhelezov.
 */
public class Main {

    private static final Logger log = Logger.getLogger(String.valueOf(Main.class));
    private static List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException {

        CustomClassLoader loader = new CustomClassLoader(
                Main.class.getClassLoader());

        Class<?> classCat = loader.loadClass("C:\\Users\\Vladislav_Zhelezov\\EPAM\\Java Education\\java-mentoring\\Garbage Collection" +
                "\\src\\main\\java\\com\\epam\\java\\training\\jvm\\task4\\source\\Cat");
        Class<?> classDog = loader.loadClass("C:\\Users\\Vladislav_Zhelezov\\EPAM\\Java Education\\java-mentoring\\Garbage Collection" +
                "\\src\\main\\java\\com\\epam\\java\\training\\jvm\\task4\\source\\Dog");
        log.info("Loaded class name: " + loader);
        //Add in list cats and dogs
        Stream.iterate(1, x -> x + 1)
                .limit(10)
                .forEach(x -> {
                    Animal cat;
                    Animal dog;
                    try {
                        cat = (Animal) classCat.newInstance();
                        dog = (Animal) classDog.newInstance();
                        animals.add(cat);
                        animals.add(dog);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        animals.forEach(animal -> {
            animal.play();
            animal.voice();
        });
    }
}
