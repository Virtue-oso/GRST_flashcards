package main;

public class FlashCards {
    private String term;
    private String definition;
    private Integer memorized;
    private Integer module;
    public TermPart type;
    public enum TermPart{
        BASE,
        SUFFIX,
        PREFIX
    }

    public FlashCards(String term, String definition, Integer module){
        this.term = term;
        this.definition = definition;
        this.memorized =0;
        this.module = module;
        if(term.charAt(0)=='-'){
            type = TermPart.SUFFIX;
        }
        if(Character.isUpperCase(term.charAt(0))){
            type = TermPart.BASE;
        }
        else{
            type = TermPart.PREFIX;
        }
    }

    public TermPart getType(){
        return this.type;
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
        return "module" + module + "Part: " + type + "\n"+term + "\n Definition: " + "   " + definition;
    }
}
