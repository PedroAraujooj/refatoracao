package questao1.classifier;

import org.example.questao1.classifier.NumberClassifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberClassifierTest {

    private final NumberClassifier classifier = new NumberClassifier();

    @Test
    void shouldReturnHighWhenValueIsGreaterThanTen() {
        assertEquals("ALTO", classifier.classify(11));
    }

    @Test
    void shouldReturnMediumWhenValueIsExactlyTen() {
        assertEquals("MÉDIO", classifier.classify(10));
    }

    @Test
    void shouldReturnLowWhenValueIsLessThanTenAndNotRareCase() {
        assertEquals("BAIXO", classifier.classify(5));
    }

    @Test
    void shouldReturnRareCaseWhenValueIsMinus9999() {
        assertEquals("CASO RARO", classifier.classify(-9999));
    }
}