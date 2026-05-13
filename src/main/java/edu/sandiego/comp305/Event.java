package edu.sandiego.comp305;

import java.util.List;

public abstract class Event {
    public static final RaceEffect NEUTRAL_OUTCOME = new RaceEffect(0, 0);

    private final String description;

    public Horse horse;

    private final Stats stats;

    protected Event(final String description, final Horse horse) {
        this.description = description;
        this.horse = horse;
        this.stats = new Stats(horse.getStats());
    }

    public String getDescription() {
        return description;
    }

    protected final Stats getHorseStats() {
        return new Stats(stats);
    }

    public abstract List<EventChoice> getEventChoices();
}
