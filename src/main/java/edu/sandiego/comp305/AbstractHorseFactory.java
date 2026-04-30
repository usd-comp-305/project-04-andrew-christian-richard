package edu.sandiego.comp305;

import java.util.Random;
/**
 *
 *   Difficulty \ Track  | 100m | 200m | 400m
 *   --------------------+------+------+------
 *   EASY                |   8  |  16  |  24
 *   MEDIUM              |  32  |  40  |  48
 *   HARD                |  56  |  64  |  72
 *
 * Overall Stats increase by 8 for each track tier and by 24 for each difficulty tier,
 *   Overall Stats = (difficultyTier * 24) + (trackDistance * 8)
 *   where difficultyTier: EASY=0, MEDIUM=1, HARD=2
 *       trackDistance:    ONE_HUNDRED_METER = 1, TWO_HUNDRED_METER = 2, FOUR_HUNDRED_METER = 3
 */
public class AbstractHorseFactory implements HorseFactory {
    private static final int MAX_STAT = 25;
    private static final int MIN_STAT = 1;
    private static final int BASE_DIFFICULTY_STAT_MULTIPLIER = 24;
    private static final int BASE_TRACK_STAT_MULTIPLIER = 8;
    private Difficulty difficulty;
    private TrackType trackType;
    private Random random;

    public AbstractHorseFactory(Difficulty difficulty, TrackType trackType, Random random) {
        this.difficulty = difficulty;
        this.trackType = trackType;
        this.random = random;
    }

    public AbstractHorseFactory() {
    }

    @Override
    public Horse createPlayerHorse(String name) {
        Stats basicStats = new Stats(MIN_STAT, MIN_STAT, MIN_STAT);
        return new Horse(name, basicStats);
    }

    @Override
    public Horse createOpponentHorse(String name) {
        Stats opponentStats = generateRandomStats();
        return new Horse(name, opponentStats);
    }

    private Stats generateRandomStats() {
        int overallStats = getOverallStatPoints();

        int speed = MIN_STAT;
        int stamina = MIN_STAT;
        int power = MIN_STAT;

        overallStats -= (3 * MIN_STAT);

        while (overallStats > 0) {
            int chosenStat = random.nextInt(3);

            if (chosenStat == 0 && speed < MAX_STAT) {
                speed++;
                overallStats--;
            } else if (chosenStat == 1 && stamina < MAX_STAT) {
                stamina++;
                overallStats--;
            } else if (chosenStat == 2 && power < MAX_STAT && power < speed) {
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
            case ONE_HUNDRED_METER -> 1;
            case TWO_HUNDRED_METER -> 2;
            case FOUR_HUNDRED_METER -> 3;
        };
    }

    private int getOverallStatPoints(){
        return (getDifficultyMultiplier() * BASE_DIFFICULTY_STAT_MULTIPLIER) +
                (getTrackTypeMultiplier() * BASE_TRACK_STAT_MULTIPLIER);
    }

}