package edu.sandiego.comp305;

public interface RaceParticipant {
    String getName();
    Stats getStats();
    int move();
    int getCurrentDistance();
    boolean hasFinished(int trackLength);
    abstract void applyRaceEffect(final RaceEffect effect);
}
