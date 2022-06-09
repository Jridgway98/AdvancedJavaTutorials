package com.joseph_ridgway.setandhashset;

public class DogMain {
    public static void main(String[] args) {
        Dog rover = new Labrador("Rover");
        Dog rover2 = new Dog("Rover");

        System.out.println(rover2.equals(rover));
        System.out.println(rover.equals(rover2));
    }
}
