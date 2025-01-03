package Compiler;

public class LabelGenerator {
    private int nextLabel = 0;

    public int getLabel(){
        int label = nextLabel;
        this.nextLabel++;
        return label;
    }

    public int getLabelCount(){
        return nextLabel;
    }
}