package com.test;

import com.domin.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class test_01 {
    public static void  main(String[] args){
        List<Apple> apples = new ArrayList<>();
        Apple a1 = new Apple("green",120);
        Apple a2 = new Apple("red",120);
        Apple a3 = new Apple("red",80);
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        List<Apple> result = apples.stream().filter((Apple a)->"green".equals(a.getColor())||70<a.getWeight()).collect(Collectors.toList());
    }
   /* public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
    public interface Predicate<T>{
        boolean test(T t);
    }
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }*/
}
