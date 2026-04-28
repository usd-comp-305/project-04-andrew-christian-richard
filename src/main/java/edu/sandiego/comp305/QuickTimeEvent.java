package edu.sandiego.comp305;

public abstract class QuickTimeEvent {
    private final String description;
    private final int triggerDistance;
    private final int staminaCost;

    protected QuickTimeEvent(String description, int triggerDistance, int staminaCost) {
        this.description = description;
        this.triggerDistance = triggerDistance;
        this.staminaCost = staminaCost;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTriggeredAt(int distance) {
        return false;
    }

    public RaceEffect applyEffect(Horse horse) {
        return null;
    }
}
