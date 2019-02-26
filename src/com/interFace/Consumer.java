package com.interFace;

@FunctionalInterface
public interface Consumer<T> {
    public void accept(T t);
}
