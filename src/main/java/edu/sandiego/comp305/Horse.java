package edu.sandiego.comp305;

public class Horse implements RaceParticipant {
    private final String name;
    private final Stats stats;
    private int currentDistance;
    private int trophies;

    public Horse(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.currentDistance = 0;
        this.trophies = 0;
    }

    public void addTrophies(int amount) {

    }

    public int getTrophies() {
        return trophies;
    }

    public void resetForRace() {

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
        return 0;
    }

    @Override
    public int getCurrentDistance() {
        return currentDistance;
    }

    @Override
    public boolean hasFinished(int trackLength) {
        return false;
    }

    @Override
    public void applyRaceEffect(RaceEffect effect) {

    }
}
