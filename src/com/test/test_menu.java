package com.test;

import com.domin.Apple;
import com.domin.CaloricLevel;
import com.domin.Dish;
import com.interFace.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class test_menu {
    private List<Dish> menu;

    @Before
    public void before() throws Exception {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
    }

    @Test
    public void test_reduceing(){
        int calories = menu.stream()
                .collect(Collectors.reducing(0,
                        Dish::getCalories,
                        Integer::sum));
    }

    @Test
    public void test_filter(){
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(a->a.getCalories()>300)
                .limit(3)
                .map(Dish::getName)
                .collect(Collectors.toList());

        //Collectors中的多种归约方法
        //求卡路里和
        menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        //求列表数量
        menu.stream()
                .collect(Collectors.counting());
        menu.stream().count();
        //求卡路里和、最大值、最小值、平均值等
        IntSummaryStatistics ss = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        ss.getCount();

        //连接字符串
        String s1 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining());
        String s2 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(","));
    }

    @Test
    public void test_menunum(){
        int  i = menu.stream().map(d->1).reduce(0,Integer::sum);
        long j = menu.stream().count();
        System.out.println(i+"    "+j);
    }
    @Test
    public void test_num(){
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        int sum = numbers.stream().reduce(0, Integer::sum);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        if(max.isPresent()){
            int max_int = max.get();
        }
        System.out.println(sum);
    }
    //分组
    @Test
    public void test_group(){
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish->{
            if(dish.getCalories()>400)
                return CaloricLevel.NORMAL;
            else if(dish.getCalories()<700 &&dish.getCalories()>=400)
                return CaloricLevel.DIET;
            else
                return CaloricLevel.FAT;
        }));
    }
    //多级分组
    @Test
    public void test_group1(){
        Map<Dish.Type,Map<CaloricLevel,List<Dish>>> map = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish->{
            if(dish.getCalories()>400)
                return CaloricLevel.NORMAL;
            else if(dish.getCalories()<700 &&dish.getCalories()>=400)
                return CaloricLevel.DIET;
            else
                return CaloricLevel.FAT;
        })));

        Map<Dish.Type,Long> map1 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));

        Map<Dish.Type, Dish> map2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,Collectors
                        .collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));

        Map<Dish.Type,Set<CaloricLevel>> map3 = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.mapping(dish->{
            if(dish.getCalories()>400)
                return CaloricLevel.NORMAL;
            else if(dish.getCalories()<700 &&dish.getCalories()>=400)
                return CaloricLevel.DIET;
            else
                return CaloricLevel.FAT;
        },Collectors.toSet())));
    }
    //分区
    @Test
    public void test_group2(){
        Map<Boolean, List<Dish>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
    }

    //测试自定义Collector
    @Test
    public void test_Collector(){
        List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());

        List<Dish> dishes1 = menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll);
    }

    @Test
    public void test_jisuan(){
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");

        System.out.println("Iterative sum done in:" +
                measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");

        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs" );
    }
    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

}