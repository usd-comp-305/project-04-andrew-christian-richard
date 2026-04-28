package edu.sandiego.comp305;

public class GoodEvent extends QuickTimeEvent {
    public GoodEvent(String description, int triggerDistance, int staminaCost) {
        super(description, triggerDistance, staminaCost);
    }

    @Override
    public RaceEffect applyEffect(Horse horse) {
        return null;
    }
}