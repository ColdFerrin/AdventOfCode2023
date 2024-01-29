package org.coldferrin.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day5Opt {

    public static void main(String[] args) {

        ArrayList<SeedRange> seeds = new ArrayList<>();

        ArrayList<ArrayList<RangeSet>> rangeSetsArrayList = new ArrayList<>();

        ArrayList<RangeSet> rangeSets = new ArrayList<>();

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day5/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";

            while ((line = inputFileReader.readLine()) != null){

                if (line.startsWith("seeds:")){
                    String[] parts = line.split(":");
                    String[] seedStrings = parts[1].strip().split("\\W+");

                    for (int i = 0; i < seedStrings.length; i += 2) {
                        Long start = Long.valueOf(seedStrings[i]);
                        Long length = Long.valueOf(seedStrings[i+1]);
                        seeds.add(new SeedRange(start, length));
                    }

                } else if (line.contains("seed-to-soil") || line.contains("soil-to-fertilizer") || line.contains("fertilizer-to-water") || line.contains("water-to-light") || line.contains("light-to-temperature") || line.contains("temperature-to-humidity") || line.contains("humidity-to-location")){
                    if (!rangeSets.isEmpty()) {
                        rangeSetsArrayList.add(rangeSets);
                        rangeSets = new ArrayList<>();
                    }
                } else if (!line.isBlank() && !line.isEmpty()){
                    generateRangeSets(line.strip().split("\\W+"), rangeSets);
                }

            }

            rangeSetsArrayList.add(rangeSets);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Long minValue = Long.MAX_VALUE;
        Long stepSize = 10L;

        for (SeedRange seedRange: seeds){

            for (Long i = seedRange.start; i < seedRange.start + seedRange.length; i += stepSize) {
                ArrayList<Long> seedsPart = new ArrayList<>();

                long end = (i + stepSize < seedRange.start + seedRange.length) ? i + stepSize : seedRange.start + seedRange.length;

                for (Long j = i; j < end; j++) {
                    seedsPart.add(j);
                }

                Long minField = moveSeeds(seedsPart, rangeSetsArrayList);

                if (minField < minValue){
                    minValue = minField;
                }
            }
        }

        System.out.println(minValue);
    }

    private static Long moveSeeds(ArrayList<Long> seeds, ArrayList<ArrayList<RangeSet>> rangeSetsArrayList) {
        ArrayList<Long> resultSeeds = new ArrayList<>();

        for (int i = 0; i < seeds.size(); i++){
            Long seed = seeds.get(i);

            for (int j = 0; j < rangeSetsArrayList.size(); j++) {

                ArrayList<RangeSet> rangeSets = rangeSetsArrayList.get(j);

                for (int k = 0; k < rangeSets.size(); k++) {
                    RangeSet rangeSet = rangeSets.get(k);

                    if (rangeSet.isInSourceRange(seed)) {
                        seed = rangeSet.getDestinationValue(seed);
                        break;
                    }
                }
            }

            resultSeeds.add(seed);
        }

        //System.out.println(resultSeeds);

        return resultSeeds.stream().mapToLong(Long::longValue).min().getAsLong();
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

    public record SeedRange(Long start, Long length){}
}
