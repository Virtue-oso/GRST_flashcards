package main;

public class FlashCards {
    private String term;
    private String definition;

    public FlashCards(String term, String definition){
        this.term = term;
        this.definition = definition;
    }

    public String getTerm(){
        return  this.term;
    }

    public String flip(){
        return this.definition;
    }

    @Override
    public String toString(){
        return term + "\n Definition: " + "   " + definition;
    }
}
