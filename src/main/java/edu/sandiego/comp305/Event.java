package edu.sandiego.comp305;

import java.util.List;

public abstract class Event {
    private final String description;

    protected Event(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public abstract List<EventChoice> getChoices();
}
