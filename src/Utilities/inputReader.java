package Utilities;

import main.FlashCards;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class inputReader {
    public static ArrayList<FlashCards> readInput(File inputFile){
        ArrayList<FlashCards> flashCardsPile = new ArrayList<>();
        try{
            FileReader file_reader= new FileReader(inputFile);
            BufferedReader buffered_reader = new BufferedReader(file_reader);
            String flash = buffered_reader.readLine();
            while(flash!=null) {
                    String[] flashParts = flash.split("\",\"");
                    flashCardsPile.add(new FlashCards(flashParts[0], flashParts[1], Integer.parseInt(flashParts[2])));
                    flash = buffered_reader.readLine();
            }
        } catch (IOException e){

        }

        return flashCardsPile;
    }
    public static ArrayList<ArrayList<FlashCards>> readInputNew(File inputFile){
        ArrayList<ArrayList<FlashCards>> flashCardsPile = new ArrayList<>();
        try{
            FileReader file_reader= new FileReader(inputFile);
            BufferedReader buffered_reader = new BufferedReader(file_reader);
            String flash = buffered_reader.readLine();
            while(flash!=null) {
                String[] flashParts = flash.split("\",\"");
                if(Integer.parseInt(flashParts[2])>flashCardsPile.size()) {
                    flashCardsPile.add(new ArrayList<FlashCards>());
                }
                flashCardsPile.get(Integer.parseInt(flashParts[2])-1).add(new FlashCards(flashParts[0], flashParts[1],
                        Integer.parseInt(flashParts[2])));
                flash = buffered_reader.readLine();
            }
        } catch (IOException e){

        }

        return flashCardsPile;
    }
}
