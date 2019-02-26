package com.other;

import com.domin.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterTest_02 {
    public static void  main(String[] args){
        List<String> arrayOfWords = new ArrayList<String>();
        arrayOfWords.add("Goodbye");
        arrayOfWords.add("World");
        List<String> uniqueCharacters =
                arrayOfWords.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.print("ok");
    }
    public void getNum(){
        List<Integer> ints = Arrays.asList(1,23,5,25,88,77,14,11);
        List<Integer> result = filter(ints,i->i>23);
        System.out.print(result);
        //排序
        result.sort(Integer::compareTo);
        System.out.print("ok");
    }

    public void getFriut(){
        List<Apple> apples = Arrays.asList(new Apple("red",101),
                new Apple("red",111),new Apple("yellow",103) );
        List<Apple> biaozhun = filter(apples,(Apple a)->a.getWeight()>101&&a.getColor().equals("red"));
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.print(biaozhun);
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

}
