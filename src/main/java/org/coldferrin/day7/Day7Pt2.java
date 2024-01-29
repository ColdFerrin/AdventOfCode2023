package org.coldferrin.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7Pt2 {

    HashMap<HandPt2, Long> hands = new HashMap<>();

    public static void main(String[] args) {

        Long total = 0L;

        Day7Pt2 day7 = new Day7Pt2();

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day7/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null) {
                String[] handParts = line.strip().split("\\W+");

                day7.hands.put(new HandPt2(handParts[0]), Long.parseLong(handParts[1]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<HandPt2> sortedHands = new ArrayList<>(day7.hands.keySet().stream().sorted(new HandPt2.handComparator()).toList().reversed());

        for (int i = sortedHands.size(); i > 0; i--) {
            total += i * day7.hands.get(sortedHands.get(i - 1));
        }

        System.out.println(sortedHands);
        System.out.println(total);
    }
}
