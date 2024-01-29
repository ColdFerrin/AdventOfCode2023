package org.coldferrin.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day5 {

    public static void main(String[] args) {

        ArrayList<Long> seeds = new ArrayList<>();

        ArrayList<RangeSet> rangeSets = new ArrayList<>();

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day5/test.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null){

                if (line.startsWith("seeds:")){
                    String[] parts = line.split(":");
                    String[] seedStrings = parts[1].strip().split("\\W+");

                    for (int i = 0; i < seedStrings.length; i += 2) {
                        Long start = Long.valueOf(seedStrings[i]);
                        Long length = Long.valueOf(seedStrings[i+1]);
                        for (long j = 0; j < length; j++) {
                            seeds.add(start + j);
                        }
                    }

                } else if (line.contains("seed-to-soil") || line.contains("soil-to-fertilizer") || line.contains("fertilizer-to-water") || line.contains("water-to-light") || line.contains("light-to-temperature") || line.contains("temperature-to-humidity") || line.contains("humidity-to-location")){
                    if (!rangeSets.isEmpty()) {
                        seeds = moveSeeds(seeds, rangeSets);
                        rangeSets = new ArrayList<>();
                    }
                } else if (!line.isBlank() && !line.isEmpty()){
                    generateRangeSets(line.strip().split("\\W+"), rangeSets);
                }

            }

            seeds = moveSeeds(seeds, rangeSets);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(seeds.stream().mapToLong(Long::longValue).min());
    }

    private static ArrayList<Long> moveSeeds(ArrayList<Long> seeds, ArrayList<RangeSet> rangeSets) {
        HashMap<Long, Long> resultSeeds = new HashMap<>();

        for (int i = 0; i < seeds.size(); i++){
            for (int j = 0; j < rangeSets.size(); j++){
                RangeSet rangeSet = rangeSets.get(j);

                if (!resultSeeds.containsKey(seeds.get(i)) && rangeSet.isInSourceRange(seeds.get(i))){
                    resultSeeds.put(seeds.get(i), rangeSet.getDestinationValue(seeds.get(i)));
                } else if (!resultSeeds.containsKey(seeds.get(i)) && j == rangeSets.size() - 1){
                    resultSeeds.put(seeds.get(i), seeds.get(i));
                }
            }
        }

        System.out.println(resultSeeds);

        return new ArrayList<>(resultSeeds.values());
    }

    public static void generateRangeSets(String[] lineParts, ArrayList<RangeSet> rangeSets){
        Long destinationStart = Long.parseLong(lineParts[0]);
        Long sourceStart = Long.parseLong(lineParts[1]);
        Long range = Long.parseLong(lineParts[2]);

        rangeSets.add(new RangeSet(sourceStart, destinationStart, range));
    }

    public static class RangeSet {
        Long sourceStart;
        Long destinationStart;
        Long range;

        public RangeSet(Long sourceStart, Long destinationStart, Long range) {
            this.sourceStart = sourceStart;
            this.destinationStart = destinationStart;
            this.range = range;
        }

        public boolean isInSourceRange(Long value){
            return value >= sourceStart && value < sourceStart + range;
        }

        public Long getDestinationValue(Long value){
            return destinationStart + (value - sourceStart);
        }
    }
}
