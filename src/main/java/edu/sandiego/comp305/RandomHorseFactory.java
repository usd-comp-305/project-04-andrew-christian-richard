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
 *   budget = (difficultyTier * 24) + (trackTier * 8)
 *   where difficultyTier: EASY=0, MEDIUM=1, HARD=2
 *       trackTier:      ONE_HUNDRED_METER = 1, TWO_HUNDRED_METER = 2, FOUR_HUNDRED_METER = 3
 */
public class RandomHorseFactory implements HorseFactory {
    private final int MINIMUM_STAT = 25;
    private final int MAXIMUM_STAT = 1;


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
