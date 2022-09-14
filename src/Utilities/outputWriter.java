package Utilities;

import main.FlashCards;

import java.io.*;
import java.util.ArrayList;

public class outputWriter {
    public static void outputFunction(ArrayList<FlashCards> flashcardPile){
        File outputFile = new File("flashcard_Save.txt");

        if(!outputFile.exists()){
            try{
                outputFile.createNewFile();
            } catch (IOException e){
                System.out.println(e);
            }
        }
        if(outputFile.exists()&& outputFile.canWrite()){
            try{
                FileWriter file_Writer = new FileWriter(outputFile, true);
                BufferedWriter buffered_writer = new BufferedWriter(file_Writer);
                FileReader file_reader= new FileReader(outputFile);
                BufferedReader buffered_reader = new BufferedReader(file_reader);
                for(int i =0; i< flashcardPile.size();i++){
                    buffered_writer.write(flashcardPile.get(i).getTerm()+"\"" +","+"\""+flashcardPile.get(i).flip()
                            +"\""+ flashcardPile.get(i).getModule()+"\n");
                }
                buffered_writer.flush();
                buffered_writer.close();
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
