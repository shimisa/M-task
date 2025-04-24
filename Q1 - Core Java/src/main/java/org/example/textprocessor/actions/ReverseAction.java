package org.example.textprocessor.actions;

import java.util.Collections;
import java.util.List;

public class ReverseAction implements Action {
    @Override
    public List<String> perform(List<String> lines) {
        Collections.reverse(lines);
        return lines;
    }

    @Override
    public String toString() {
        return "reverse";
    }
}
