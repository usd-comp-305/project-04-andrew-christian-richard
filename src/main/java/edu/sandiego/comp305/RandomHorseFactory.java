package edu.sandiego.comp305;

import java.util.Random;

public class RandomHorseFactory implements HorseFactory {
    private final int minStat;
    private final int maxStat;

    public RandomHorseFactory(int minStat, int maxStat) {
        this.minStat = minStat;
        this.maxStat = maxStat;
    }

    @Override
    public Horse createPlayerHorse(String name) {
        return null;
    }

    @Override
    public Horse createOpponentHorse(String name) {
        return null;
    }

    private Stats generateRandomStats() {
        return null;
    }
}
