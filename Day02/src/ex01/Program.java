package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Program {
    private static TreeSet<String> dictionary = new TreeSet<>();
    static String pathDictionary = "dictionary.txt";
    private static ArrayList<Integer> frequencyA = new ArrayList<>();
    private static ArrayList<Integer> frequencyB = new ArrayList<>();

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Invalid number of arguments. Enter two arguments");
            }
            File fileA = new File(args[0]);
            File fileB = new File(args[1]);
            calculateFrequency(fileA, fileB);
            determineSimilarities();
        } catch (IOException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void createDictionary(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathDictionary))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        dictionary.add(word);
                    }
                }
                bufferedWriter.write(String.valueOf(dictionary).replaceAll("\\p{Punct}", ""));
            }
        }
    }

    static String[] readInputFile(File path) throws IOException {
        String[] words = new String[0];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split(" ");
            }
        }
        return words;
    }

    static void calculateFrequency(File fileA, File fileB) throws IOException {
        createDictionary(fileA);
        createDictionary(fileB);
        String[] inputFileA = readInputFile(fileA);
        String[] inputFileB = readInputFile(fileB);
        frequencyA = countWords(inputFileA);
        frequencyB = countWords(inputFileB);
    }

    static ArrayList<Integer> countWords(String[] input) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (String word : dictionary) {
            int countFrequency = 0;
            for (String s : input) {
                if (word.equals(s)) {
                    countFrequency++;
                }
            }
            tmp.add(countFrequency);
        }
        return tmp;
    }

    static void determineSimilarities() {
        int numerator = calculateNumerator();
        double denominator = calculateDenominator();
        double similarity = Math.floor(numerator / denominator * 100) / 100.0;
        System.out.println("Similarity = " + similarity);
    }

    static int calculateNumerator() {
        int numerator = 0;
        for (int i = 0; i < frequencyA.size(); i++) {
            numerator = numerator + frequencyA.get(i) * frequencyB.get(i);
        }
        return numerator;
    }

    static double calculateDenominator() {
        double supportDenominatorA = 0.0;
        double supportDenominatorB = 0.0;

        for (int i = 0; i < frequencyA.size(); i++) {
            supportDenominatorA = supportDenominatorA + frequencyA.get(i) * frequencyA.get(i);
            supportDenominatorB = supportDenominatorB + frequencyB.get(i) * frequencyB.get(i);
        }

        double sqrt1 = Math.sqrt(supportDenominatorA);
        sqrt1 = Math.floor(sqrt1 * 100) / 100.0;

        double sqrt2 = Math.sqrt(supportDenominatorB);
        sqrt2 = Math.floor(sqrt2 * 100) / 100.0;

        double denominator = sqrt1 * sqrt2;
        denominator = Math.floor(denominator * 100) / 100.0;
        return denominator;
    }
}
