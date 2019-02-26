package com.test;

import com.domin.Apple;
import com.interFace.Consumer;
import com.interFace.Function;
import com.interFace.Predicate;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test_01 {
    private List<Apple> apples;
    private List<String> listOfStrings;

    @Before
    public void before() throws Exception {
        apples = new ArrayList<>();
        Apple a1 = new Apple("green",120);
        Apple a2 = new Apple("red",120);
        Apple a3 = new Apple("red",80);
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        listOfStrings = new ArrayList<>();
        listOfStrings.add("");
        listOfStrings.add("apple");
        listOfStrings.add("orange");
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
        Thread thread = new Thread(()->{
            System.out.println("多线程oo");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            System.out.println("多线程oo");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("oo");
    }
    @Test
    public void test_Predicate(){
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println("oo");
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }
    @Test
    public void test_Consumer(){
        foEach(Arrays.asList(1,2,3,4,5),(Integer i)->System.out.println(i));
        System.out.println("ok");
    }
    public static <T> void foEach(List<T> list, Consumer<T> consumer){
        for(T t:list){
            consumer.accept(t);
        }
    }
    @Test
    public void test_Function(){
        List<Integer> list = map(Arrays.asList("lambdas","in","action"),(String s)->s.length());
        System.out.println(list);
    }
    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }

}