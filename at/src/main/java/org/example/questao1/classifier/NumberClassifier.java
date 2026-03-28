package org.example.questao1.classifier;

public class NumberClassifier {

    public String classify(int value) {
        if (isRareCase(value)) {
            return "CASO RARO";
        }

        if (isHigh(value)) {
            return "ALTO";
        }

        if (isMedium(value)) {
            return "MÉDIO";
        }

        return "BAIXO";
    }

    public void printClassification(int value) {
        System.out.println(classify(value));
    }

    private boolean isRareCase(int value) {
        return value == -9999;
    }

    private boolean isHigh(int value) {
        return value > 10;
    }

    private boolean isMedium(int value) {
        return value == 10;
    }
}