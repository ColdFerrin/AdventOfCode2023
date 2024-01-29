package org.coldferrin.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    public static void main(String[] args) {

        Long sum = 0L;

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day4/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null){

                Long cardVal = getLineValue(line);

                sum += cardVal;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(sum);
    }

    private static Long getLineValue(String line) {
        Long cardVal = 0L;

        String[] gameParts = line.split(":");

        String[] gameRoundParts = gameParts[1].split("\\|");

        ArrayList<String> winningNumbers = new ArrayList<>(List.of(gameRoundParts[0].strip().split("\\W+")));

        ArrayList<String> cardNumbers = new ArrayList<>(List.of(gameRoundParts[1].strip().split("\\W+")));

        for (String cardNumber : cardNumbers){
            if (winningNumbers.contains(cardNumber)) {
                if (cardVal == 0){
                    cardVal = 1L;
                } else {
                    cardVal *= 2;
                }
            }
        }
        return cardVal;
    }
}
