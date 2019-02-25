package com.test;

import com.domin.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
    public void test_num(){
        System.out.println("oo");
    }

}