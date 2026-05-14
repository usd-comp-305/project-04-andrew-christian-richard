package edu.sandiego.comp305;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class HorseRace {

    // Utility class: prevent instantiation
    private HorseRace() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(final String[] args) {

        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        final RaceManager raceManager = new RaceManager();

        final HorseFactory playerHorseFactory =
                new AbstractPlayerHorseFactory();

        final UpgradeSystem upgradeSystem =
                new HorseUpgradeSystem();

        final Display display = new Display();

        final HorseRacingGame game =
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
