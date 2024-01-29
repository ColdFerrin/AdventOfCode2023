package org.coldferrin.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3Pt2 {
    public static void main(String[] args) {
        ArrayList<String> rows = new ArrayList<>();

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day3/input_leon.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){

            String line = "";
            while ((line = inputFileReader.readLine()) != null){

                rows.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int numGears = 0;

        long sum = 0;

        Pattern symbolPattern = Pattern.compile("[*]");

        for (int row = 0; row < rows.size(); row++) {
            Matcher symbolMatcher = symbolPattern.matcher(rows.get(row));

            while (symbolMatcher.find()){

                HashMap<Location, Long> partNumbers = new HashMap<>();

                int column = symbolMatcher.start();

                if (row - 1 >= 0 && column - 1 >= 0){
                    findNumberOn(row - 1, column - 1, rows, partNumbers);
                }
                if (row - 1 >= 0){
                    findNumberOn(row - 1, column, rows, partNumbers);
                }
                if (row - 1 >= 0 && column + 1 < rows.get(column).length()){
                    findNumberOn(row - 1, column + 1, rows, partNumbers);
                }
                if (column - 1 >= 0){
                    findNumberOn(row, column - 1, rows, partNumbers);
                }
                if (column + 1 < rows.get(column).length()){
                    findNumberOn(row, column + 1, rows, partNumbers);
                }
                if (row + 1 < rows.size() && column - 1 >= 0){
                    findNumberOn(row + 1, column - 1, rows, partNumbers);
                }
                if (row + 1 < rows.size()){
                    findNumberOn(row + 1, column , rows, partNumbers);
                }
                if (row + 1 < rows.size() && column + 1 < rows.get(column).length()){
                    findNumberOn(row + 1, column + 1, rows, partNumbers);
                }

                if (partNumbers.size() == 2){
                    numGears ++;
                    long product = 1;
                    for (Long aLong : partNumbers.values()){
                        product *= aLong;
                    }

                    System.out.println(partNumbers);

                    sum += product;
                }
            }
        }

        System.out.println(sum);
        System.out.println(numGears);

    }

    public static void findNumberOn(int row, int column, List<String> map, HashMap<Location, Long> partNumbers){
        Character current = map.get(row).charAt(column);

        if (!Character.isDigit(current)){
            return;
        }

        int startCol = column;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(current);

        for (int i = column - 1; i >= 0 && Character.isDigit(map.get(row).charAt(i)); i--) {
            stringBuilder.insert(0,map.get(row).charAt(i));
            startCol--;
        }

        for (int i = column + 1; i < map.get(row).length() && Character.isDigit(map.get(row).charAt(i)); i++) {
            stringBuilder.append(map.get(row).charAt(i));
        }

        Location key = new Location(row, startCol, stringBuilder.length());

        if (!partNumbers.containsKey(key)){
            partNumbers.put(key, Long.parseLong(stringBuilder.toString()));
        }
    }

    public record Location (int rowNum, int colNum, int length) {}
}
