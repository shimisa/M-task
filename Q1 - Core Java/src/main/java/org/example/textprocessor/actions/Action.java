package org.example.textprocessor.actions;

import java.util.List;

/// Implement this interface to create a new action.
///
///
/// Steps:
///  1. Implement this interface.
///  2. Override `toString()` to return the action name.
///  3. Register your class in:
///    src/main/resources/META-INF/services/com.example.textprocessor.Action

public interface Action {
    List<String> perform(List<String> lines);
}
