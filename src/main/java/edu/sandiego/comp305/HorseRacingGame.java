package edu.sandiego.comp305;

import java.util.Scanner;

public class HorseRacingGame {
    private final RaceManager raceManager;
    private final UpgradeSystem progressionSystem;
    private final Scanner scanner;
    private final Display display;
    private Horse playerHorse;
    private final HorseFactory playerHorseFactory;
    private int totalTrophies;

    public HorseRacingGame(final Horse playerHorse, final RaceManager raceManager, final HorseFactory playerHorseFactory,
                           final UpgradeSystem progressionSystem, Scanner scanner, Display display){
        this.playerHorse = null;
        this.raceManager = raceManager;
        this.progressionSystem = progressionSystem;
        this.scanner = scanner;
        this.display = display;
        this.totalTrophies = 0;
        this.playerHorseFactory = playerHorseFactory;
    }

    public void startGame() {
        display.printWelcome();
        display.printHorseCreation();
        String horseName = scanner.nextLine();
        this.playerHorse = playerHorseFactory.createHorse(horseName);
    }

    public void runRace(final Race race) {

    }

    public void handlePostRaceRewards(final Race race) {

    }

}
