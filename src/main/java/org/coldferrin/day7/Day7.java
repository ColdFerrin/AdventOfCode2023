package org.coldferrin.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7 {

    HashMap<Hand, Long> hands = new HashMap<>();

    public static void main(String[] args) {

        Long total = 0L;

        Day7 day7 = new Day7();

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day7/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null) {
                String[] handParts = line.strip().split("\\W+");

                day7.hands.put(new Hand(handParts[0]), Long.parseLong(handParts[1]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Hand> sortedHands = new ArrayList<>(day7.hands.keySet().stream().sorted(new Hand.handComparator()).toList().reversed());

        for (int i = sortedHands.size(); i > 0; i--) {
            total += i * day7.hands.get(sortedHands.get(i - 1));
        }

        System.out.println(sortedHands);
        System.out.println(total);
    }
}
