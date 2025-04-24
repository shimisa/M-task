package org.example.textprocessor;

import java.io.IOException;

public class ErrorHandler {
    public static void handle(Exception e) {
        String prefix = (e instanceof IOException) ? "I/O Error: " : "Error: ";
        System.err.println(prefix + e.getMessage());
    }
}