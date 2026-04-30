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
 * Budget increases by 8 for each track tier and by 24 for each difficulty tier,
 * so the formula is:
 *   budget = (difficultyTier * 24) + (trackDistance * 8)
 *   where difficultyTier: EASY=0, MEDIUM=1, HARD=2
 *       trackDistance:    ONE_HUNDRED_METER = 1, TWO_HUNDRED_METER = 2, FOUR_HUNDRED_METER = 3
 */
public class AbstractHorseFactory implements HorseFactory {
    private static final int MINIMUM_STAT = 25;
    private static final int MAXIMUM_STAT = 1;
    private static final int DIFFICULTY_STAT_MULTIPLIER = 24;
    private static final int TRACK_STAT_MULTIPLIER = 8;
    private Difficulty difficulty;
    private Track track;
    private Random random;

    public AbstractHorseFactory(Difficulty difficulty, Track track) {
        this.difficulty = difficulty;
        this.track = track;
        this.random = new Random();
    }

    public AbstractHorseFactory() {
    }

    @Override
    public Horse createPlayerHorse(String name) {
        return null;
    }

    @Override
    public Horse createOpponentHorse(String name) {
        return null;
    }

    private Stats generateRandomStats() {
        return null;
    }
}
