package com.test;

import com.domin.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class test_01 {
    private List<Apple> apples;

    @Before
    public void before() throws Exception {
        apples = new ArrayList<>();
        Apple a1 = new Apple("green",120);
        Apple a2 = new Apple("red",120);
        Apple a3 = new Apple("red",80);
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        System.out.println("before");
    }

    @Test
    public void test_filter(){
        System.out.println("oo");
        List<Apple> result = apples.stream().filter((Apple a)->"green".equals(a.getColor())).collect(Collectors.toList());
        System.out.println("oo");
    }
    @Test
    public void test_compare(){
        System.out.println("oo");
        /*apples.sort(new Comparator<Apple>() {
            //compareTo这个方法Integer才有，int没有
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });*/
        apples.sort((Apple a,Apple b)->a.getWeight().compareTo(b.getWeight()));
        System.out.println("oo");
    }
    @Test
    public void test_runnable(){
        System.out.println(this+"oo");
        Thread thread = new Thread(()->System.out.println("多线程oo"));
        System.out.println("oo");
    }


}