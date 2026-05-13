package edu.sandiego.comp305;

public interface RaceParticipant {
    public abstract String getName();

    public abstract Stats getStats();

    public abstract int move();
    public abstract int getCurrentDistance();

    public abstract boolean hasFinished(final int trackLength);

    public abstract void applyRaceEffect(final RaceEffect effect);
}
