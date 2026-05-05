package edu.sandiego.comp305;

import java.util.List;

public abstract class Event {
    static final RaceEffect NEUTRAL_OUTCOME = new RaceEffect(0, 0);
    private final String description;
    public Horse horse;

    protected Event(String description, Horse horse){
        this.description = description;
        this.horse = horse;
    }

    public String getDescription(){
        return description;
    }

    public abstract List<EventChoice> getEventChoices();
}
