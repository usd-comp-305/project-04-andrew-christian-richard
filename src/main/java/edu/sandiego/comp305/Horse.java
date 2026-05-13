package edu.sandiego.comp305;

public class Horse implements RaceParticipant {
    private static final int STAMINA_DEPLETION_INTERVAL = 2;

    private static final int STAMINA_DEPLETION_RATE = 1;

    private final String name;

    private final Stats stats;

    private int currentDistance;

    private int trophyCount;

    private int roundsMoved;

    private RaceEffect raceEffect;

    public Horse(
            final String name,
            final Stats stats) {
        this.name = name;
        this.stats = stats;
        this.currentDistance = 0;
        this.trophyCount = 0;
        this.roundsMoved = 0;
        this.raceEffect = RaceEffect.NO_EFFECT;
    }

    public void addTrophies(final int amount) {
        trophyCount += amount;
    }

    public int getTrophyCount() {
        return trophyCount;
    }

    public void resetCurrentDistance() {
        currentDistance = 0;
        roundsMoved = 0;
        raceEffect = RaceEffect.NO_EFFECT;
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
        final int spacesMoved = stats.generateMovement(raceEffect);
        currentDistance += spacesMoved;

        if (spacesMoved > 0) {
            roundsMoved++;
            decreaseStaminaEvery2Rounds();
        }

        return spacesMoved;
    }

    private void decreaseStaminaEvery2Rounds() {
        if (roundsMoved % STAMINA_DEPLETION_INTERVAL == 0) {
            stats.consumeStamina(STAMINA_DEPLETION_RATE);
        }
    }

    @Override
    public int getCurrentDistance() {
        return currentDistance;
    }
}
