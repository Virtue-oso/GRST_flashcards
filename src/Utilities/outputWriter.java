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
                for(int i =0; i< flashcardPile.size();i++){
                    buffered_writer.write(flashcardPile.get(i).getTerm()+"\",\""+flashcardPile.get(i).flip()
                            +"\",\""+ flashcardPile.get(i).getModule()+"\n");
                }
                buffered_writer.flush();
                buffered_writer.close();
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }
    public static void outputFunctionNew(ArrayList<ArrayList<FlashCards>> flashcardPile){
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
                for (int j = 0;j<flashcardPile.size();j++) {
                    for (int i = 0; i < flashcardPile.get(j).size(); i++) {
                        buffered_writer.write(flashcardPile.get(j).get(i).getTerm() + "\",\"" + flashcardPile.get(j)
                                .get(i).flip()+ "\",\"" + flashcardPile.get(j).get(i).getModule() + "\n");
                    }
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
