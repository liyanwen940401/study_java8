package com.interFace;

@FunctionalInterface
public interface Predicate<T> {
    public boolean test(T t);
}

