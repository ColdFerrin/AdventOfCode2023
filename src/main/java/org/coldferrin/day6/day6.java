package org.coldferrin.day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class day6 {

    public static void main(String[] args) {

        Integer winOptions = 1;

        String[] times = new String[0];

        String[] distances = new String[0];

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day6/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null) {
                String[] parts = line.strip().split(":");
                if (parts[0].equalsIgnoreCase("time")){
                    times = parts[1].strip().split("\\W+");
                } else if (parts[0].equalsIgnoreCase("distance")){
                    distances = parts[1].strip().split("\\W+");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < times.length; i++) {
            int time = Integer.parseInt(times[i]);
            int distanceRecord = Integer.parseInt(distances[i]);

            int numWins = 0;

            for (int j = 0; j <= time; j++) {
                int distance = j*(time - j);

                if (distance > distanceRecord){
                    numWins++;
                }
            }

            winOptions *= numWins;
        }

        System.out.println(winOptions);

    }
}
