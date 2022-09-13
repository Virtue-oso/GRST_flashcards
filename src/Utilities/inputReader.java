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
            String inputCategories = buffered_reader.readLine();
            if (inputCategories != null){

            }
        } catch (IOException e){

        }

        return flashCardsPile;
    }
}
