package Compiler;

import java.util.Hashtable;

public class LabelGenerator {
    private int nextLabel = 1;

    public int getLabel(){
        /*int label = nextLabel;
        nextLabel++;*/
        this.nextLabel++;
        return nextLabel;
    }

    public int getLabelCount(){
        return nextLabel;
    }
}