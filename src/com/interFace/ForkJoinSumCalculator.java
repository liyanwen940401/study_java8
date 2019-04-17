package com.interFace;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 用分支、合并框架求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;

    private final int start;

    private final int end;

    public static final long THRESHOLD = 10_000;

    //用于构建主任务
    public ForkJoinSumCalculator(long[] numbers){
        this(numbers,0,numbers.length);
    }

    //私有构造函数用于递归方式为主任务创建子任务
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end-start;
        if(length<=THRESHOLD){
            return computeSequentially();
        }
        //创建子任务，为数组前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+length/2);
        //利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        //创建子任务，为数组后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2,end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        return rightResult+leftResult;
    }

    private Long computeSequentially(){
        Long sum = 0L;
        for(int i = start; i<end;i++){
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
