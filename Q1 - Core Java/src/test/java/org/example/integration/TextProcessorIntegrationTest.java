package org.example.integration;

import org.example.file.FileHandler;
import org.example.textprocessor.TextProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextProcessorIntegrationTest {
    @Test
    void shouldProcessValidInputFile(@TempDir Path tempDir) throws IOException {
        // Setup test files and execute processor
        FileHandler handler = new FileHandler();
        Path inputFile = tempDir.resolve("input.txt");
        Path outputFile = tempDir.resolve("output.txt");
        List<String> content = List.of("test line");
        handler.writeFile(inputFile.toString(), content);
        String[] args = {
                inputFile.toString(),
                outputFile.toString(),
                "reverse" // Assuming "reverse" is a valid action
        };

        TextProcessor processor = new TextProcessor();
        assertDoesNotThrow(() -> processor.process(args));
    }
}