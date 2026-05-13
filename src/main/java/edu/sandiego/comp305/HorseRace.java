package edu.sandiego.comp305;

import java.util.Scanner;

public class HorseRace {

    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);

        RaceManager raceManager = new RaceManager();

        HorseFactory playerHorseFactory =
                new AbstractPlayerHorseFactory();

        UpgradeSystem upgradeSystem =
                new HorseUpgradeSystem();

        Display display = new Display();

        HorseRacingGame game =
                new HorseRacingGame(
                        null,
                        raceManager,
                        playerHorseFactory,
                        upgradeSystem,
                        scanner,
                        display
                );

        game.runGame();

        scanner.close();
    }
}