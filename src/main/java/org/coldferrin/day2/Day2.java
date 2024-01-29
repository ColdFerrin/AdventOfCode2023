package org.coldferrin.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    public static void main(String[] args) {
        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day2/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){
            BigInteger sum = new BigInteger("0");

            String line = "";
            while ((line = inputFileReader.readLine()) != null){
                String[] gameParts = line.split(":");

                Pattern numberPattern = Pattern.compile("\\d+");

                Matcher gameNumberMatcher = numberPattern.matcher(gameParts[0]);

                gameNumberMatcher.find();
                String gameNumber = gameNumberMatcher.group();

                String[] gameRoundParts = gameParts[1].split(";");

                long redMax = 0;

                long greenMax = 0;

                long blueMax = 0;

                for (String gameRoundPart : gameRoundParts){

                    String[] gameRoundColorParts = gameRoundPart.split(",");

                    for (String gameRoundColorPart : gameRoundColorParts) {

                        String[] pair = gameRoundColorPart.strip().split(" ");

                        String color = pair[1];
                        long number = Long.parseLong(pair[0]);

                        if ("red".equalsIgnoreCase(color) && number > redMax){
                            redMax = number;
                        }

                        if ("green".equalsIgnoreCase(color) && number > greenMax){
                            greenMax = number;
                        }

                        if ("blue".equalsIgnoreCase(color) && number > blueMax){
                            blueMax = number;
                        }

                    }
                }

                BigInteger product = BigInteger.valueOf(redMax * greenMax * blueMax);

                sum = sum.add(product);

            }

            System.out.println(sum);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
