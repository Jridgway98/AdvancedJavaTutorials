package com.joseph_ridgway.abstractclass;

public class Penguin extends Bird{

    public Penguin(String name) {
        super(name);
    }

    @Override
    public void fly() {
        super.fly();
        System.out.println("I'm not very good at that. Can I go for a swim instead?");
    }
}
