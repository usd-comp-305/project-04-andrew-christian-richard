package edu.sandiego.comp305;

public class NeutralEvent extends QuickTimeEvent {
    public NeutralEvent(String description, int triggerDistance, int staminaCost) {
        super(description, triggerDistance, staminaCost);
    }

    @Override
    public RaceEffect applyEffect(Horse horse) {
        return null;
    }
}