package edu.sandiego.comp305;

public class Horse implements RaceParticipant {

    private static final int INITIAL_UPGRADE_POINTS = 9;

    private final String name;

    private final Stats stats;

    private int currentDistance;

    private int trophyCount;

    private int currentUpgradePoints;

    private RaceEffect raceEffect;

    public Horse(
            final String name,
            final Stats stats) {
        this.name = name;
        this.stats = stats;
        this.currentDistance = 0;
        this.trophyCount = 0;
        this.currentUpgradePoints = INITIAL_UPGRADE_POINTS;
        this.raceEffect = RaceEffect.NO_EFFECT;
    }

    public void addTrophies(final int amount) {
        trophyCount += amount;
        currentUpgradePoints = amount;
    }

    public void changeCurrentUpgradePoints(final int amount) {
        currentUpgradePoints = amount;
    }

    public int getTrophyCount() {
        return trophyCount;
    }

    public int getCurrentUpgradePoints() {
        return currentUpgradePoints;
    }

    public void resetCurrentDistance() {
        currentDistance = 0;
        raceEffect = RaceEffect.NO_EFFECT;
    }

    public RaceEffect getRaceEffect(){
        return raceEffect;
    }

    public void resetForCurrentRace() {
        resetCurrentDistance();
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
    public boolean hasFinished(final int trackLength) {
        return currentDistance >= trackLength;
    }

    @Override
    public void applyRaceEffect(final RaceEffect effect) {
        raceEffect = effect;
    }

    @Override
    public int move() {
        final int spacesMoved =
                stats.generateMovement(raceEffect);

        currentDistance += spacesMoved;

        return spacesMoved;
    }

    @Override
    public int getCurrentDistance() {
        return currentDistance;
    }
}
