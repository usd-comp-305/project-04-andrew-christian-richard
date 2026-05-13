package edu.sandiego.comp305;

public class HorseRacingGame {
    private final Horse playerHorse;

    private final RaceManager raceManager;

    private final UpgradeSystem progressionSystem;

    private final EventFactory eventFactory;

    private final HorseFactory horseFactory;

    public HorseRacingGame(
            final Horse playerHorse,
            final RaceManager raceManager,
            final UpgradeSystem progressionSystem,
            final EventFactory eventFactory,
            final HorseFactory horseFactory) {
        this.playerHorse = new Horse(playerHorse);
        this.raceManager = raceManager;
        this.progressionSystem = progressionSystem;
        this.eventFactory = eventFactory;
        this.horseFactory = horseFactory;
    }

    public void startGame() {

    }

    public void runRace(final Race race) {

    }

    public void handlePostRaceRewards(final Race race) {

    }
}
