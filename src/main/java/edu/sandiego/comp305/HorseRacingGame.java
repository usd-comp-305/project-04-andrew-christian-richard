package edu.sandiego.comp305;

import java.util.Scanner;

public class HorseRacingGame {
    private static final int TOTAL_UPGRADE_POINTS = 5;
    private final RaceManager raceManager;
    private final UpgradeSystem upgradeSystem;
    private final Scanner scanner;
    private final Display display;
    private Horse playerHorse;
    private final HorseFactory playerHorseFactory;
    private int totalTrophies;

    public HorseRacingGame(final Horse playerHorse, final RaceManager raceManager, final HorseFactory playerHorseFactory,
                           final UpgradeSystem progressionSystem, Scanner scanner, Display display){
        this.playerHorse = null;
        this.raceManager = raceManager;
        this.upgradeSystem = progressionSystem;
        this.scanner = scanner;
        this.display = display;
        this.totalTrophies = 0;
        this.playerHorseFactory = playerHorseFactory;
    }

    public void runGame() {
        display.printWelcome();
        createPlayerHorse();
        upgradeHorse(TOTAL_UPGRADE_POINTS);

        while(raceManager.hasNoMoreRaces()){
            Race race = raceManager.getNextRace();
            runRace(race);
        }


    }

    private void runRace(final Race race) {
        race.startRace();
    }

    public void handlePostRaceRewards(final Race race) {
    }

    private void createPlayerHorse(){
        display.printHorseCreation();
        String horseName = scanner.nextLine();

        while (horseName.isBlank()) {
            horseName = scanner.nextLine();
        }
        this.playerHorse = playerHorseFactory.createHorse(horseName);
    }

    private void upgradeHorse(final int upgradePoints) {
        display.printUpgradeSystem(upgradePoints);

        boolean validUpgrade = false;

        while (!validUpgrade) {
            String upgradeInput = scanner.nextLine();

            try {
                upgradeSystem.applyUpgrade(playerHorse, upgradeInput);
                validUpgrade = true;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter upgrades: ");
            }
        }
    }


}
