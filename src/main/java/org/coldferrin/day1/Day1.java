package org.coldferrin.day1;

import java.io.*;
import java.math.BigInteger;

public class Day1 {

    public static void main(String[] args) throws IOException {

        File inputFile = new File(Thread.currentThread().getContextClassLoader().getResource("day1/input.txt").getFile());
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile))){
            BigInteger sum = new BigInteger("0");

            String line = "twone";
            while ((line = inputFileReader.readLine()) != null){

                for (int i = 0; i < line.length(); i++) {
                    if (line.startsWith("one", i)){
                        line = line.replaceFirst("one", "o1e");
                    } else if (line.startsWith("two", i)){
                        line = line.replaceFirst("two", "t2o");
                    } else if (line.startsWith("three", i)){
                        line = line.replaceFirst("three", "t3e");
                    } else if (line.startsWith("four", i)){
                        line = line.replaceFirst("four", "f4r");
                    } else if (line.startsWith("five", i)){
                        line = line.replaceFirst("five", "f5e");
                    } else if (line.startsWith("six", i)){
                        line = line.replaceFirst("six", "s6x");
                    } else if (line.startsWith("seven", i)){
                        line = line.replaceFirst("seven", "s7n");
                    } else if (line.startsWith("eight", i)){
                        line = line.replaceFirst("eight", "e8t");
                    } else if (line.startsWith("nine", i)){
                        line = line.replaceFirst("nine", "n9e");
                    }

                }

                line = line.replaceAll("[a-z]", "");

                String number = new StringBuilder().append(line.charAt(0)).append(line.charAt(line.length()-1)).toString();

                sum = sum.add(new BigInteger(number));
            }

            System.out.println(sum);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
