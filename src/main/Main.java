package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import Utilities.*;


public class Main {
    public static void main(String[] args){
        ArrayList<FlashCards> flashcardPile = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter term (enter 0 to start studying): ");
        String term = userInput.nextLine();
        while (term !="") {
            System.out.println("Please enter the definition for " + term);
            String definition = userInput.nextLine();
            flashcardPile.add(new FlashCards(term, definition));
            System.out.println("Please enter term (enter 0 to save or enter to start studying): ");
            term = userInput.nextLine();
        }
        inputReader.inputFunction(flashcardPile);
        System.out.println("**************** Study Time ****************");
        while (userInput.nextLine() == ""){
            int limit = flashcardPile.size();
            Random rand = new Random();
            int flashNumber = rand.nextInt(limit);
            System.out.println("\n********************************\n your term is: "+ flashcardPile.get(flashNumber).getTerm());
            System.out.println("press enter to flip flashcard");
            if (userInput.nextLine() ==""){
                System.out.println("the definition for " + flashcardPile.get(flashNumber).getTerm()+" is :\n" +
                        flashcardPile.get(flashNumber).flip());
            }
        }
    }
}
