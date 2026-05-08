package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class HorseTests {
    private static final int TEST_SPEED = 1;
    private static final int TEST_POWER = 1;
    private static final int TEST_STAMINA = 1;

    private static final int MOVE_DISTANCE = 5;

    private static final int TROPHIES_TO_ADD = 5;

    private static final int TEST_TRACK_LENGTH = 10;

    @Test
    void constructor_setsNameStatsDistanceTrophies() {
        final int EXPECTED_DISTANCE_AND_TROPHY_COUNT = 0;

        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);
        final Horse testHorse = new Horse("Nunu", testStats);

        assertEquals("Nunu", testHorse.getName());
        assertEquals(testStats, testHorse.getStats());
        assertEquals(EXPECTED_DISTANCE_AND_TROPHY_COUNT, testHorse.getCurrentDistanceRan());
        assertEquals(EXPECTED_DISTANCE_AND_TROPHY_COUNT, testHorse.getTrophyCount());
    }

    @Test
    void addTrophies_increasesTrophyCount() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        testHorse.addTrophies(TROPHIES_TO_ADD);

        assertEquals(TROPHIES_TO_ADD, testHorse.getTrophyCount());
    }

    @Test
    void move_increasesDistanceRan_byMoveDistance() {
        final Stats testStats = mock(Stats.class);
        when(testStats.generateMovement()).thenReturn(MOVE_DISTANCE);

        final Horse testHorse = new Horse("Nunu", testStats);

        final int distanceRan = testHorse.move();

        assertEquals(MOVE_DISTANCE, distanceRan);
        assertEquals(MOVE_DISTANCE, testHorse.getCurrentDistanceRan());
    }

    @Test
    void hasFinished_returnsFalse_DistanceLessThanTrackLength() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));

        assertFalse(testHorse.hasFinished(TEST_TRACK_LENGTH));
    }

    @Test
    void hasFinished_returnsTrue_DistanceEqualsTrackLength() {
        final Stats testStats = mock(Stats.class);
        when(testStats.generateMovement()).thenReturn(MOVE_DISTANCE, MOVE_DISTANCE);

        final Horse testHorse = new Horse("Nunu", testStats);

        testHorse.move();
        testHorse.move();

        assertEquals(TEST_TRACK_LENGTH, testHorse.getCurrentDistanceRan());
        assertTrue(testHorse.hasFinished(TEST_TRACK_LENGTH));
    }

    @Test
    void reset_resetsDistance_keepsTrophies() {
        final int EXPECTED_TROPHY_COUNT = 10;
        final int EXPECTED_DISTANCE = 0;

        final Stats testStats = mock(Stats.class);
        when(testStats.generateMovement()).thenReturn(MOVE_DISTANCE);

        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        testHorse.addTrophies(TROPHIES_TO_ADD);
        testHorse.addTrophies(TROPHIES_TO_ADD);

        testHorse.move();
        testHorse.resetForCurrentRace();

        assertEquals(EXPECTED_DISTANCE, testHorse.getCurrentDistanceRan());
        assertEquals(EXPECTED_TROPHY_COUNT, testHorse.getTrophyCount());
    }

    @Test
    void applyRaceEffect_appliesEffect() {
        final int SPEED_CHANGE = 1;
        final int POWER_CHANGE = 1;

        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));

        testHorse.applyRaceEffect(new RaceEffect(SPEED_CHANGE, POWER_CHANGE));

        assertEquals(TEST_SPEED + SPEED_CHANGE, testHorse.getStats().getSpeed());
        assertEquals(TEST_POWER + POWER_CHANGE, testHorse.getStats().getPower());
    }
}