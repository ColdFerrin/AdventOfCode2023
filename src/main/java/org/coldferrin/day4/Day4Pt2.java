package org.coldferrin.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day4Pt2 {
    public static void main(String[] args) {

        ArrayList<String> lines = new ArrayList<>();

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day4/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null){

                lines.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<Integer, Integer> resultLines = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {

            String line = lines.get(i);

            if (!resultLines.containsKey(i)){
                resultLines.put(i,1);
            } else {
                resultLines.replace(i, resultLines.get(i)+1);
            }

            int numMatches = 0;

            String[] gameParts = line.split(":");

            String[] gameRoundParts = gameParts[1].split("\\|");

            ArrayList<String> winningNumbers = new ArrayList<>(List.of(gameRoundParts[0].strip().split("\\W+")));

            ArrayList<String> cardNumbers = new ArrayList<>(List.of(gameRoundParts[1].strip().split("\\W+")));

            for (String cardNumber : cardNumbers){
                if (winningNumbers.contains(cardNumber)) {
                    numMatches++;
                }
            }

            for (int j = i+1; j <= i+numMatches; j++) {
                if (!resultLines.containsKey(j)){
                    resultLines.put(j,resultLines.get(i));
                } else {
                    resultLines.replace(j, resultLines.get(i)+resultLines.get(j));
                }
            }
        }

        System.out.println(resultLines.values().stream().mapToInt(value -> value).sum());
    }
}
