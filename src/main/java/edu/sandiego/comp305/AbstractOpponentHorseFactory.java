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
 *   EASY                |   8  |  32  |  56
 *   MEDIUM              |  16  |  40  |  64
 *   HARD                |  24  |  48  |  72

 */
public class AbstractOpponentHorseFactory implements HorseFactory {
    private static final int BASE_DIFFICULTY_STAT_MULTIPLIER = 8;

    private static final int BASE_TRACK_STAT_MULTIPLIER = 24;

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

    @Override
    public Horse createHorse(final String name) {
        final Stats opponentStats = generateRandomStats();
        return new Horse(name, opponentStats);
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
        final int difficultyStatPoints = (getDifficultyMultiplier() + 1)
                * BASE_DIFFICULTY_STAT_MULTIPLIER;

        return difficultyStatPoints
                + (getTrackTypeMultiplier() * BASE_TRACK_STAT_MULTIPLIER);
    }
}
