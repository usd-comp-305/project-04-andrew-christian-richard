package edu.sandiego.comp305;

public class Horse implements RaceParticipant {
    private final String name;
    private final Stats stats;
    private int currentDistanceRan;
    private int trophyCount;

    public Horse(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.currentDistanceRan = 0;
        this.trophyCount = 0;
    }

    public void addTrophies(int amount) {
        trophyCount += amount;
    }

    public int getTrophyCount() {
        return trophyCount;
    }

    public void resetForCurrentRace() {
        currentDistanceRan = 0;
    }

    public int getCurrentDistanceRan() {
        return currentDistanceRan;
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
        return currentDistanceRan >= trackLength;
    }

    @Override
    public void applyRaceEffect(RaceEffect effect) {
        effect.apply(this);
    }
}
