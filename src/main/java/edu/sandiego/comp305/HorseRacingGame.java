package edu.sandiego.comp305;

import java.util.Scanner;
import java.util.List;
import java.util.function.Supplier;

public class HorseRacingGame {

    private static final int EXTRA_UPGRADE_POINTS = 4;

    private static final int FIRST_TOTAL_UPGRADE_POINTS = 12;

    private static final int FIRST_CHOICE = 1;

    private static final int LAST_CHOICE = 3;

    private final RaceManager raceManager;

    private final UpgradeSystem upgradeSystem;

    private final Supplier<String> scanner;

    private final Display display;

    private Horse playerHorse;

    private final HorseFactory playerHorseFactory;

    private int totalTrophies;

    public HorseRacingGame(
            final Horse playerHorse,
            final RaceManager raceManager,
            final HorseFactory playerHorseFactory,
            final UpgradeSystem progressionSystem,
            final Scanner scanner,
            final Display display) {
        this.playerHorse = playerHorse;
        this.raceManager = raceManager;
        this.upgradeSystem = progressionSystem;
        this.scanner = scanner::nextLine;
        this.display = display;
        this.totalTrophies = 0;
        this.playerHorseFactory = playerHorseFactory;
    }

    public void runGame() {
        display.printWelcome();
        createPlayerHorse();
        upgradeHorse(FIRST_TOTAL_UPGRADE_POINTS);

        while(raceManager.hasMoreRaces()){
            final Race race = raceManager.getNextRace(playerHorse);
            runRace(race);
            handlePostRaceRewards(race);
        }
        display.printCompletion(playerHorse, totalTrophies);
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

    private void handlePostRaceRewards(final Race race) {
        final Placement placement = race.getPlacement(playerHorse);
        final int trophiesEarned = placement.getTrophyValue();
        totalTrophies += trophiesEarned;
        display.printRaceResult(race, trophiesEarned, totalTrophies);
        upgradeHorse(trophiesEarned + EXTRA_UPGRADE_POINTS);
    }

    private void createPlayerHorse(){
        display.printHorseCreation();
        String horseName = scanner.get();

        while (horseName.isBlank()) {
            horseName = scanner.get();
        }
        this.playerHorse = playerHorseFactory.createHorse(horseName);
    }

    private void upgradeHorse(final int upgradePoints) {
        display.printUpgradeSystem(upgradePoints);
        playerHorse.setCurrentUpgradePoints(upgradePoints);

        boolean validUpgrade = false;

        while (!validUpgrade) {
            final String upgradeInput = scanner.get();

            try {
                upgradeSystem.applyUpgrade(playerHorse, upgradeInput);
                validUpgrade = true;
            } catch (final IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter upgrades: ");
            }
        }
    }

    private void handleEvent(final Race race) {
        display.printEvent(race);

        final Event event = race.getEvent();
        final List<EventChoice> choices = event.getEventChoices();

        final int choiceNumber = readChoice();
        final EventChoice selectedChoice = choices.get(choiceNumber - 1);

        race.resolveEvent(selectedChoice);
    }

    private int readChoice() {
        while (true) {
            final String input = scanner.get();

            try {
                final int choice = Integer.parseInt(input);

                if (choice >= FIRST_CHOICE && choice <= LAST_CHOICE) {
                    return choice;
                }
            } catch (final NumberFormatException exception) {
                System.out.print("Try again!");
            }
            System.out.print("Choose (1-3): ");
        }
    }

    private void waitForEnter() {
        System.out.print("Press Enter to continue to the next round...");
        scanner.get();
    }
}
