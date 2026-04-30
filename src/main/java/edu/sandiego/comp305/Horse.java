package edu.sandiego.comp305;

public class Horse implements RaceParticipant {
    private final String name;
    private final Stats stats;
    private int currentDistance;
    private int trophyCount;

    public Horse(String name, Stats stats) {
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

    public void resetForRace() {
        currentDistance = 0;
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
    public int move() {
        currentDistance += stats.getSpeed();
        return currentDistance;
    }

    @Override
    public int getCurrentDistance() {
        return currentDistance;
    }

    @Override
    public boolean hasFinished(int trackLength) {
        return currentDistance >= trackLength;
    }

    @Override
    public void applyRaceEffect(RaceEffect effect) {
        effect.apply(this);
    }
}
