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
}
