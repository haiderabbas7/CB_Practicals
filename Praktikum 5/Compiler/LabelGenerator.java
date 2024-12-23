package Compiler;

public class LabelGenerator {
    private static int nextLabel = 0;

    public int getLabel(){
        int label = nextLabel;
        nextLabel++;
        return label;
    }

    public int getLabelCount(){
        return nextLabel;
    }
}