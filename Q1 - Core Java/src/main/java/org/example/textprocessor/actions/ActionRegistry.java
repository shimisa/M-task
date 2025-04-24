package org.example.textprocessor.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class ActionRegistry {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        ServiceLoader<Action> loader = ServiceLoader.load(Action.class);
        for (Action action : loader) {
            actions.put(action.toString().toLowerCase(), action);
        }
    }

    public static Action getAction(String name) {
        return actions.get(name.toLowerCase());
    }
}
