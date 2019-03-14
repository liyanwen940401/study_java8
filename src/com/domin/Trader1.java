package com.domin;

public class Trader1 {
    private final String name;
    private final Long birthday;
    public Trader1(String n, Long birthday){
        this.name = n;
        this.birthday = birthday;
    }
    public String getName(){
        return this.name;
    }
    public Long getBirthday(){
        return this.birthday;
    }

}
