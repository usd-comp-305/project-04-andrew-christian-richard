package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HorseTests {
    private static final int TEST_SPEED = 5;

    private static final int TEST_POWER = 5;
    
    private static final int TEST_STAMINA = 2;

    private static final int MOVE_DISTANCE = 5;

    private static final int TROPHIES_TO_ADD = 5;

    private static final int TEST_TRACK_LENGTH = 10;

    private Horse testHorse;

    @BeforeEach
    void init() {
        final Stats testStats =
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testHorse = new Horse("Nunu", testStats);
    }

    @Test
    void constructor_setsNameStatsDistanceTrophies() {
        final int expectedDistanceAndTrophyCount = 0;

        assertEquals("Nunu", testHorse.getName());

        assertEquals(
                expectedDistanceAndTrophyCount,
                testHorse.getCurrentDistanceRan());

        assertEquals(
                expectedDistanceAndTrophyCount,
                testHorse.getTrophyCount());
    }

    @Test
    void addTrophies_increasesTrophyCount() {
        testHorse.addTrophies(TROPHIES_TO_ADD);

        assertEquals(TROPHIES_TO_ADD, testHorse.getTrophyCount());
    }

    @Test
    void move_increasesDistanceByMoveDistance() {
        final int distanceRan = testHorse.move();

        assertEquals(MOVE_DISTANCE, distanceRan);
        assertEquals(MOVE_DISTANCE, testHorse.getCurrentDistanceRan());
    }

    @Test
    void hasFinished_returnsFalse_DistanceLessThanTrackLength() {
        assertFalse(testHorse.hasFinished(TEST_TRACK_LENGTH));
    }

    @Test
    void hasFinished_returnsTrue_DistanceEqualsTrackLength() {
        testHorse.move();
        testHorse.move();

        assertEquals(TEST_TRACK_LENGTH, testHorse.getCurrentDistanceRan());
        assertTrue(testHorse.hasFinished(TEST_TRACK_LENGTH));
    }

    @Test
    void reset_resetsDistance_keepsTrophies() {
        final int expectedTrophyCount = 10;
        final int expectedDistance = 0;

        testHorse.addTrophies(TROPHIES_TO_ADD);
        testHorse.addTrophies(TROPHIES_TO_ADD);

        testHorse.move();
        testHorse.resetForCurrentRace();

        assertEquals(expectedDistance, testHorse.getCurrentDistanceRan());
        assertEquals(expectedTrophyCount, testHorse.getTrophyCount());
    }

    @Test
    void move_consumesStaminaEveryTwoRounds() {
        testHorse.move();

        assertEquals(TEST_STAMINA, testHorse.getStats().getStamina());

        testHorse.move();

        assertEquals(TEST_STAMINA - 1, testHorse.getStats().getStamina());
    }

    @Test
    void applyRaceEffect_appliesEffect() {
        final int speedChange = 1;
        final int powerChange = 1;

        testHorse.applyRaceEffect(new RaceEffect(speedChange, powerChange));

        final int distanceRan = testHorse.move();

        assertEquals(TEST_SPEED + speedChange, distanceRan);
        assertEquals(TEST_SPEED, testHorse.getStats().getSpeed());
        assertEquals(TEST_POWER, testHorse.getStats().getPower());
    }
}
