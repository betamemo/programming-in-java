package com.harbourspace.lesson06;

import java.util.Optional;

public class OptionalTask {

    public static Optional<String> getWord(String text, String word) {
        if (text.contains(word)) {
            return Optional.of(word);
        } else {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        String text = "Chocolate is a food product made from roasted and ground cocoa pods mixed with fat and powdered sugar to produce a solid confectionery.";
        System.out.println(text);

        String word = "Cake";
        Optional<String> result = getWord(text, word);

        if (result.isPresent()) {
            System.out.println("Word found: " + result.get());
        } else {
            System.out.println("Word not found: " + word);
        }
    }

}
