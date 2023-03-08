package initialUnnamedFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1: Add new word");
            System.out.println("2: Delete word");
            System.out.println("3: Get meaning");
            System.out.println("4: Dictionary list");
            System.out.println("5: Spell check a text file");
            System.out.println("6: Exit");

            int option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter word: ");
                    String newWord = input.next().toLowerCase();
                    System.out.print("Enter meaning: ");
                    String meaning = input.next();
                    if (dict.add(newWord, meaning)) {
                        System.out.println(newWord + " added to dictionary.");
                    } else {
                        System.out.println(newWord + " already exists in dictionary.");
                    }
                    break;
                case 2:
                    System.out.print("Enter word to delete: ");
                    String wordToDelete = input.next().toLowerCase();
                    if (dict.delete(wordToDelete)) {
                        System.out.println(wordToDelete + " deleted from dictionary.");
                    } else {
                        System.out.println(wordToDelete + " not found in dictionary.");
                    }
                    break;
                case 3:
                    System.out.print("Enter word to get meaning: ");
                    String wordToGetMeaning = input.next().toLowerCase();
                    String meaningOutput = dict.getMeaning(wordToGetMeaning);
                    if (meaningOutput != null) {
                        System.out.println("Meaning of " + wordToGetMeaning + ": " + meaningOutput);
                    } else {
                        System.out.println(wordToGetMeaning + " not found in dictionary.");
                    }
                    break;
                case 4:
                    System.out.println(dict.printWordList());
                    break;
                case 5:
                    System.out.print("Enter file name: ");
                    String fileName = input.next();
                    File file = new File(fileName);
                    try {
                        Scanner fileScanner = new Scanner(file);
                        while (fileScanner.hasNext()) {
                            String word = fileScanner.next().toLowerCase().replaceAll("[.,]", "");
                            if (!dict.exists(word)) {
                                System.out.println(word + " not found in dictionary.");
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
