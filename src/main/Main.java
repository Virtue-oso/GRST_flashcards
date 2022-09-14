package main;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import Utilities.*;


public class Main {
    public static void main(String[] args){
        ArrayList<FlashCards> flashcardPile = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("input 1 to add new terms, or press enter to load into study mode");
        String input = userInput.nextLine();
        if(input.equals("1")) {
            System.out.println("Please enter term (press enter start studying): ");
            String term = userInput.nextLine();
            while (term != "") {
                System.out.println("Please enter the definition for " + term);
                String definition = userInput.nextLine();
                System.out.println("Please enter which module this term is from");
                Integer module = userInput.nextInt();
                flashcardPile.add(new FlashCards(term, definition, module));
                System.out.println("Please enter term (press enter to start studying): ");
                term = userInput.nextLine();
            }
            outputWriter.outputFunction(flashcardPile);
        }
        if (args.length == 1) {
            File inputFile = new File(args[0]);
            flashcardPile = inputReader.readInput(inputFile);
        } else if (args.length > 1){
            System.out.println("Error! Too many arguments inputted. Continuing to main program without data import.");
        }

        System.out.println("**************** Study Time ****************");
        while (userInput.nextLine() == ""){
            int limit = flashcardPile.size();
            Random rand = new Random();
            int flashNumber = rand.nextInt(limit);
            if(flashcardPile.get(flashNumber).getMemorized()<3) {
                System.out.println("\n********************************\n your term is: " + flashcardPile.get(flashNumber).getTerm());
                System.out.println("press enter to flip flashcard");
                if (userInput.nextLine() == "") {
                    System.out.println("the definition for " + flashcardPile.get(flashNumber).getTerm() + " is :\n" +
                            flashcardPile.get(flashNumber).flip());
                    System.out.println("Did you get this term right?");
                    if (userInput.nextLine() == "y") {
                        flashcardPile.get(flashNumber).rightAnswer();
                    }
                }
            }
        }
    }
}
