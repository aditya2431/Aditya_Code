package com.ubs.opsit.interviews;

/**
 * Created by bbdnet10033 on 9/10/2018.
 */
public class MainClass {
    public static void main(String args[]){
        TimeConverter berlinTime = new BerlinTimeConverter();
        System.out.println(berlinTime.convertTime(args[0]));

    }
}
