package edu.sandiego.comp305;

public class Horse implements RaceParticipant {
    private final String name;
    private final Stats stats;
    private int currentDistance;
    private int trophyCount;

    public Horse(final String name, final Stats stats) {
        this.name = name;
        this.stats = stats;
        this.currentDistance = 0;
        this.trophyCount = 0;
    }

    public void addTrophies(int amount) {
        trophyCount += amount;
    }

    public int getTrophyCount() {
        return trophyCount;
    }

    public void resetForCurrentRace() {
        currentDistance = 0;
    }

    public int getCurrentDistanceRan() {
        return currentDistance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public boolean hasFinished(int trackLength) {
        return currentDistance >= trackLength;
    }

    @Override
    public void applyRaceEffect(final RaceEffect effect) {
        effect.apply(this);
    }

    @Override
    public int move() {
        final int spacesMoved = stats.generateMovement();
        currentDistance += spacesMoved;

        return spacesMoved;
    }

    @Override
    public int getCurrentDistance() {
        return currentDistance;
    }
}
