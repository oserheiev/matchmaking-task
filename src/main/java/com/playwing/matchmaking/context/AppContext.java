package com.playwing.matchmaking.context;

import java.util.HashMap;
import java.util.Map;

public class AppContext {

    private final Map<String, Object> context = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        return (T) context.get(name);
    }

    public void setBean(String name, Object bean) {
        context.put(name, bean);
    }
}
