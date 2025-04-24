package org.example.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    public List<String> readFile(String inputFile) throws IOException {
        return Files.readAllLines(Paths.get(inputFile));
    }

    public void writeFile(String outputFile, List<String> contents) throws IOException {
        Files.write(Paths.get(outputFile), contents);
    }
}