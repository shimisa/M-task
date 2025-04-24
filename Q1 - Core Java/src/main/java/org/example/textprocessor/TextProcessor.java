package org.example.textprocessor;

import org.example.file.FileHandler;
import org.example.textprocessor.actions.Action;
import org.example.textprocessor.actions.ActionRegistry;

import java.io.IOException;
import java.util.List;

public class TextProcessor {
    private final FileHandler fileHandler;

    public TextProcessor() {
        this.fileHandler = new FileHandler();
    }

    public void process(String[] args) throws IOException {
        validateArgs(args);
        processFile(args[0], args[1], args[2]);
    }

    private void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Usage: java Main <inputFile> <outputFile> <actionName>"
            );
        }
    }

    private void processFile(String inputFile, String outputFile, String actionName) throws IOException {
        List<String> lines = fileHandler.readFile(inputFile);
        Action action = getValidatedAction(actionName);
        List<String> result = action.perform(lines);
        fileHandler.writeFile(outputFile, result);
    }

    private Action getValidatedAction(String actionName) {
        Action action = ActionRegistry.getAction(actionName);
        if (action == null) {
            throw new IllegalArgumentException("Unknown action: " + actionName);
        }
        return action;
    }
}