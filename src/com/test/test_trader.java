package com.test;

import com.domin.Dish;
import com.domin.Trader;
import com.domin.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test_trader {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void test_filter(){
        //找出2011年的所有交易并按交易额排序（从低到高）
        transactions.stream().filter(((Transaction t)->t.getYear()==2011))
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("**************************");
        //交易员都在哪些不同的城市工作过
        List<String> traders = transactions.stream()
                .map(t->t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("**************************");
        Set<String> traders_ = transactions.stream()
                .map(t->t.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println("**************************");
        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> traders1 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t->t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("**************************");
        //返回所有交易员的姓名字符串，按字母顺序排序
        String names = transactions.stream()
                .map(t->t.getTrader().getName())
                .sorted()
                .collect(Collectors.joining());
        System.out.println("**************************");
        String names_ = transactions.stream()
                .map(t->t.getTrader().getName())
                .sorted()
                .reduce("",(a,b)->a+b);
        System.out.println("**************************");
        //打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Milan"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("**************************");
        //所有交易中，最高的交易额是多少
        Optional<Integer> max = transactions.stream()
                .map(t->t.getValue())
                .reduce(Integer::max);
        System.out.println("**************************");
        //找到交易额最小的交易
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }
    @Test
    public void test_compare(){
       /* Optional<Transaction> b =transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Milan"))
                .findAny();*/
       //有没有交易员是在米兰工作的
        boolean b = transactions.stream().anyMatch(t->t.getTrader().getCity().equals("Milan"));
        System.out.println("**************************");

    }
    //打印斐波那契数列
    @Test
    public void test_iterate(){
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[1]+t[0]})
                .limit(10)
                .forEach(t-> System.out.println("("+t[0]+","+t[1]+")"));


        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
        //生成无线流
        Stream.generate(Math::random).map(t->t*1000).limit(8).forEach(System.out::println);
    }
}