package main;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Random;
import Utilities.*;


public class Main {
    public static void main(String[] args){
        ArrayList<FlashCards> flashcardPile = new ArrayList<>();
//        ArrayList<FlashCards[]> flashcardPilesRevamp = new ArrayList<FlashCards>[]();
        ArrayList<ArrayList<FlashCards>> flashPiles = new ArrayList<ArrayList<FlashCards>>();
        Scanner userInput = new Scanner(System.in);
        if (args.length == 1) {
            File inputFile = new File(args[0]);
            flashPiles = inputReader.readInputNew(inputFile);
//            flashcardPilesRevamp = inputReader.readInput(inputFile);
        } else if (args.length > 1){
            System.out.println("Error! Too many arguments inputted. Continuing to main program without data import.");
        }
        System.out.println("input 1 to add new terms, or press enter to load into study mode");
        String input = userInput.nextLine();
        if(input.equals("1")) {
            System.out.println("Please enter which module this term is from");
            Integer module = userInput.nextInt();
            System.out.println("what type of term is this?\n 1: prefix\n 2: base\n 3: suffix");
            Integer type = userInput.nextInt();
            System.out.println("Please enter term (press enter start studying): ");
            userInput.nextLine();
            String term = userInput.nextLine();
            while (term != "") {
                int termCount = 1;
                for(int i = 0; i <term.length(); i++){
                    if(term.charAt(i) == ','){
                        termCount++;
                    }
                }
                String[] terms;
                if (termCount>1){
                    terms = term.split(",");
                }
                else {
                    terms = new String[1];
                    terms[0]=term;
                }
                if(term.equals("n")){
                    System.out.println("what type of term is this?\n 1: prefix\n 2: base\n 3: suffix");
                    type = userInput.nextInt();
                }
                String formatedTerm="";
                if(type ==1){
                    for (String word: terms) {
                        word = word+"-";
                        formatedTerm +=word;
                        if(!word.equals(terms[termCount-1]+"-")){
                            formatedTerm+=", ";
                        }
                    }
                }
                if(type ==2){
                    for (String word: terms) {
                        word = word.substring(0,1).toUpperCase() + word.substring(1)+"-";
                        formatedTerm +=word;
                        if(!word.toLowerCase(Locale.ROOT).equals(terms[termCount-1]+"-")){
                            formatedTerm+=", ";
                        }
                    }
                }
                if(type == 3){
                    for (String word: terms) {
                        word = "-"+word;
                        formatedTerm +=word;
                        if(!word.equals("-"+terms[termCount-1])){
                            formatedTerm+=", ";
                        }
                    }
                }
                System.out.println("Please enter the definition for " + term);
                String definition = userInput.nextLine();
                if(flashPiles.size()<module){
                    flashPiles.add(new ArrayList<FlashCards>());
                }
                if(!term.equals("n")) {
                    flashcardPile.add(new FlashCards(formatedTerm, definition, module));
                    flashPiles.get(module - 1).add(new FlashCards(formatedTerm, definition, module));
                }
                System.out.println("Please enter term enter \"n\" to go enter new term type" +
                        "(press enter to start studying): ");
                term = userInput.nextLine();
            }
            outputWriter.outputFunction(flashcardPile);
        }

        System.out.println("**************** Study Time ****************\n what module do you want to study?: ");
        String moduleAdd = userInput.nextLine();
        while (moduleAdd != ""){
//            System.out.println("Enter:\n  1: to study just prefixes\n  2: to study just bases\n  3: to study suffixes" + TODO add module parts
//                    "\n or press enter to study the whole module");
//            String modulePart = userInput.nextLine();
//            if(modulePart != ""){
//                int partIndex =Integer.parseInt(modulePart)-1;
//                flashcardPile.addAll(flashcardPilesRevamp.get(Integer.parseInt(moduleAdd)-1)[]);
//            }

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
            String term = flashcardPile.get(flashNumber).getTerm();
            if(flashcardPile.get(flashNumber).getMemorized()<3) {
                if(Character.isUpperCase(term.charAt(0))){
                    term = term.toUpperCase(Locale.ROOT);
                }
                System.out.println("\n********************************\n your term is: " + term);
                System.out.println("press enter to flip flashcard");
                if (userInput.nextLine()
                        == "") {
                    System.out.println("the definition for " + term + " is :\n" +
                            flashcardPile.get(flashNumber).flip());
                    System.out.println("Did you get this term right? enter y for yes or m for memorized");
                    String correctAns = userInput.nextLine();
                    if (correctAns.equals("y")) {
                        flashcardPile.get(flashNumber).rightAnswer();
                        System.out.println("you got this flashcard right, "+flashcardPile.get(flashNumber).getMemorized()
                        +" times! ");
                        if(flashcardPile.get(flashNumber).getMemorized()==3){
                            flashcardPile.remove(flashNumber);
                            System.out.println( term +" memorized and removed from the list "+ flashcardPile.size() +
                                    " words left");
                        }
                    }
                    if(correctAns.equals("m")){
                        flashcardPile.remove(flashNumber);
                        System.out.println(term +" memorized and removed from the list "+ flashcardPile.size() +
                                " words left");
                    }
                }
            }
        }
        System.out.println("Congrats you have successfully gone over all terms consistently!");
    }
}
