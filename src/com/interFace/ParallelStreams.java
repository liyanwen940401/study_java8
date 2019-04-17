package com.interFace;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    //iterate迭代式
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }
    //顺序归纳
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }
    //iterate并行归纳
    public static long parallelSum(long n) {
        return Stream.iterate(1L,i->i+1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum)
        ;
    }
    //rangeClosed顺序归纳
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    //rangeClosed并行归纳
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
