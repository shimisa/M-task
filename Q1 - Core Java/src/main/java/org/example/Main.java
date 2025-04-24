package org.example;

import org.example.textprocessor.TextProcessor;
import org.example.textprocessor.ErrorHandler;

public class Main {
    public static void main(String[] args) {
        try {
            TextProcessor processor = new TextProcessor();
            processor.process(args);
        } catch (Exception e) {
            ErrorHandler.handle(e);
            System.exit(1);
        }
    }
}