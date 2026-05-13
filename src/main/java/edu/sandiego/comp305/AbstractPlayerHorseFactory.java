package edu.sandiego.comp305;

public class AbstractPlayerHorseFactory implements HorseFactory {
    @Override
    public Horse createHorse(final String name) {
        final Stats basicStats = new Stats(MIN_STAT, MIN_STAT, MIN_STAT);
        return new Horse(name, basicStats);
    }
}
