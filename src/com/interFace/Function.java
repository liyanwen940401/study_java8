package com.interFace;

@FunctionalInterface
public interface Function <T,R>{
    public R apply (T t);
}
