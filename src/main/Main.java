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
        ArrayList<ArrayList<FlashCards>> flashPiles = new ArrayList<ArrayList<FlashCards>>();
        Scanner userInput = new Scanner(System.in);
        if (args.length == 1) {
            File inputFile = new File(args[0]);
            flashPiles = inputReader.readInputNew(inputFile);
        } else if (args.length > 1){
            System.out.println("Error! Too many arguments inputted. Continuing to main program without data import.");
        }
        System.out.println("input 1 to add new terms, or press enter to load into study mode");
        String input = userInput.nextLine();
        if(input.equals("1")) {
            System.out.println("Please enter which module this term is from");
            Integer module = userInput.nextInt();
            System.out.println("Please enter term (press enter start studying): ");
            userInput.nextLine();
            String term = userInput.nextLine();
            while (term != "") {
                System.out.println("Please enter the definition for " + term);
                String definition = userInput.nextLine();
                flashcardPile.add(new FlashCards(term, definition, module));
                if(flashPiles.size()<module){
                    flashPiles.add(new ArrayList<FlashCards>());
                }
                flashPiles.get(module-1).add(new FlashCards(term,definition,module));
                System.out.println("Please enter term (press enter to start studying): ");
                term = userInput.nextLine();
            }
            outputWriter.outputFunction(flashcardPile);
        }

        System.out.println("**************** Study Time ****************\n what module do you want to study?: ");
        String moduleAdd = userInput.nextLine();
        while (moduleAdd != ""){
            flashcardPile.addAll(flashPiles.get(Integer.parseInt(moduleAdd)-1));
            System.out.println("Anymore modules? (press enter to start studying)");
            moduleAdd = userInput.nextLine();
        }
        while (moduleAdd == ""){
            if(flashcardPile.size()==0){
                break;
            }
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
                    String correctAns = userInput.nextLine();
                    if (correctAns.equals("y")) {
                        flashcardPile.get(flashNumber).rightAnswer();
                        System.out.println("you got this flashcard right, "+flashcardPile.get(flashNumber).getMemorized()
                        +" times! ");
                    }
                }
            }
            if(flashcardPile.get(flashNumber).getMemorized()==3){
                flashcardPile.remove(flashNumber);
            }
        }
        System.out.println("Congrats you have successfully gone over all terms consistently!");
    }
}
