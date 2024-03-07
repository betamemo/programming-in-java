package com.harbourspace.lesson06;

public class MathTask {

    public static double randomAndRound(double min, double max, int decimalPlaces) {
        double randomNumber = Math.random() * (max - min) + min;
        double roundNumber = Math.round(randomNumber * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
        return roundNumber;
    }

}
