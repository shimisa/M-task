package org.example.unit;

import org.example.textprocessor.TextProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {
    @Test
    void shouldValidateArguments() {
        TextProcessor processor = new TextProcessor();
        String[] args = {"input.txt"};

        assertThrows(IllegalArgumentException.class,
                () -> processor.process(args));
    }

    @Test
    void shouldValidateActionName() {
        TextProcessor processor = new TextProcessor();
        String[] args = {"input.txt", "output.txt", "invalidAction"};

        assertThrows(IllegalArgumentException.class,
                () -> processor.process(args));
    }
}