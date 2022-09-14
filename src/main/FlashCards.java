package main;

public class FlashCards {
    private String term;
    private String definition;
    private Integer memorized;
    private Integer module;
    public FlashCards(String term, String definition, Integer module){
        this.term = term;
        this.definition = definition;
        this.memorized =0;
        this.module = module;
    }

    public String getTerm(){
        return  this.term;
    }

    public String flip(){
        return this.definition;
    }

    public String getModule(){
        String modString = this.module.toString();
        return modString;
    }

    public void rightAnswer(){
        this.memorized++;
    }

    public int getMemorized(){
        Integer correctTimes = this.memorized;
        return correctTimes;
    }

    @Override
    public String toString(){
        return "module" + module + "\n"+term + "\n Definition: " + "   " + definition;
    }
}
