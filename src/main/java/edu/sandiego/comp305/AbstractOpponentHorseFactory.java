package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.function.IntUnaryOperator;

/**
 *
 *   Difficulty \ Track  | 100m | 200m | 400m
 *   --------------------+------+------+------
 *   EASY                |  12  |  36  |  60
 *   MEDIUM              |  20  |  44  |  68
 *   HARD                |  28  |  52  |  76

 */
public class AbstractOpponentHorseFactory implements HorseFactory {
    private static final int BASE_STAT_POINTS = 12;

    private static final int DIFFICULTY_STAT_INCREMENT = 8;

    private static final int TRACK_STAT_INCREMENT = 24;

    private final Difficulty difficulty;

    private final TrackType trackType;

    private final String[] horseNames = {
        "Nugget", "Warrior", "Sunny", "Clipper", "Lake", "Blazer",
        "Thunder", "Magic", "Celtic", "Maverick", "Spur", "Jazz",
        "Cavalier", "Hawk", "Raptor", "Sixer", "Net", "Bully",
        "Bucky", "Pacer", "Piston", "Wizard", "Timber", "King",
        "Rocket", "Heat", "Hornet", "Grizzly", "Pelican"
    };

    private final Random random;

    private final IntUnaryOperator randomIntGenerator;

    public AbstractOpponentHorseFactory(
            final Difficulty difficulty,
            final TrackType trackType,
            final IntUnaryOperator randomIntGenerator) {
        this.difficulty = difficulty;
        this.trackType = trackType;
        this.random = new Random();
        this.randomIntGenerator = randomIntGenerator;
    }

    @Override
    public Horse createHorse(final String name) {
        final Stats opponentStats = generateRandomStats();
        return new Horse(name, opponentStats);
    }

    public List<Horse> createOpponentHorses(final int numberOfOpponents) {
        final List<String> availableNames =
                new ArrayList<>(List.of(horseNames));
        Collections.shuffle(availableNames, random);

        final List<Horse> opponents = new ArrayList<>();

        for (int i = 0; i < numberOfOpponents; i++) {
            final String horseName = availableNames.get(i);
            opponents.add(createHorse(horseName));
        }

        return opponents;
    }

    private Stats generateRandomStats() {
        int overallStats = getOverallStatPoints();

        int speed = MIN_STAT;
        int stamina = MIN_STAT;
        int power = MIN_STAT;

        overallStats -= (3 * MIN_STAT);

        while (overallStats > 0) {
            final int chosenStat = randomIntGenerator.applyAsInt(3);

            if (chosenStat == 0 && speed < MAX_STAT) {
                speed++;
                overallStats--;
            }

            if (chosenStat == 1 && stamina < MAX_STAT) {
                stamina++;
                overallStats--;
            }

            if (chosenStat == 2 && power < MAX_STAT && power < speed) {
                power++;
                overallStats--;
            }
        }
        return new Stats(speed, stamina, power);
    }

    private int getDifficultyMultiplier() {
        return switch (difficulty) {
            case EASY -> 0;
            case MEDIUM -> 1;
            case HARD -> 2;
        };
    }

    private int getTrackTypeMultiplier() {
        return switch (trackType) {
            case ONE_HUNDRED_METER -> 0;
            case TWO_HUNDRED_METER -> 1;
            case FOUR_HUNDRED_METER -> 2;
        };
    }

    private int getOverallStatPoints() {
        return BASE_STAT_POINTS
                + (getDifficultyMultiplier() * DIFFICULTY_STAT_INCREMENT)
                + (getTrackTypeMultiplier() * TRACK_STAT_INCREMENT);
    }
}
