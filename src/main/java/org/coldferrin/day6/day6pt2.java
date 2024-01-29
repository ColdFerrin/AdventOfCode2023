package org.coldferrin.day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class day6pt2 {

    public static void main(String[] args) {

        Long winOptions = 1L;

        String[] times = new String[0];

        String combinedTime = "";

        String[] distances = new String[0];

        String combinedDistance = "";

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
            combinedTime = combinedTime + times[i];
            combinedDistance = combinedDistance + distances[i];
        }

        long numWins = 0;
        long combinedTimeLong = Long.parseLong(combinedTime);
        long combinedDistanceLong = Long.parseLong(combinedDistance);

        for (long j = 0; j <= combinedTimeLong; j++) {
            long distance = j*(combinedTimeLong - j);

            if (distance > combinedDistanceLong){
                numWins++;
            }
        }

        winOptions = numWins;

        System.out.println(winOptions);

    }
}
