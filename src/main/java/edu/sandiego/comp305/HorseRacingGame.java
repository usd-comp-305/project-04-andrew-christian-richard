package edu.sandiego.comp305;

import java.util.Scanner;
import java.util.List;

public class HorseRacingGame {
    private static final int TOTAL_UPGRADE_POINTS = 5;
    private static final int FIRST_CHOICE = 1;
    private static final int LAST_CHOICE = 3;
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

        while(raceManager.hasMoreRaces()){
            Race race = raceManager.getNextRace();
            runRace(race);
        }


    }

    private void runRace(final Race race) {
        race.start();

        while (!race.isFinished()) {
            display.printRound(race);

            waitForEnter();

            race.prepareRound();

            if (race.hasEvent()) {
                handleEvent(race);
            }

            race.executeRound();
        }
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

    private void handleEvent(final Race race) {
        display.printEvent(race);

        Event event = race.getEvent();
        List<EventChoice> choices = event.getEventChoices();

        int choiceNumber = readChoice();
        EventChoice selectedChoice = choices.get(choiceNumber - 1);

        race.resolveEvent(selectedChoice);
    }

    private int readChoice() {
        while (true) {
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                if (choice >= FIRST_CHOICE && choice <= LAST_CHOICE) {
                    return choice;
                }
            } catch (NumberFormatException exception) {System.out.print("Try again!");}
            System.out.print("Choose (1-3): ");
        }
    }


    private void waitForEnter() {
        System.out.print("Press Enter to continue to the next round...");
        scanner.nextLine();
    }


}
