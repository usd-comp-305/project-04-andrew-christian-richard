package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatsTests {
    private static final int TEST_SPEED = 6;
    private static final int TEST_POWER = 10;
    private static final int TEST_STAMINA = 3;

    private static final int TEST_NO_STAMINA = 0;
    private static final int TEST_DEPLETED_STAMINA = 2;

    private static final int TEST_MODIFY_STATS_AMOUNT = 2;

    @Test
    void generateMovement_returnedDistanceBetweenPowerAndSpeed() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        final int distanceRan = testStats.generateMovement();

        assertTrue(distanceRan >= TEST_STAMINA);
        assertTrue(distanceRan <= TEST_SPEED);
    }

    @Test
    void generateMovement_returnsZero_NoStamina() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_NO_STAMINA, TEST_POWER);

        final int distanceRan = testStats.generateMovement();

        assertEquals(TEST_NO_STAMINA, distanceRan);
    }

    @Test
    void generateMovement_depletesStamina() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testStats.generateMovement();

        assertEquals(TEST_DEPLETED_STAMINA, testStats.getStamina());
    }

    @Test
    void consumeStamina_depletesStaminaToZero() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testStats.consumeStamina(TEST_STAMINA);
        testStats.consumeStamina(TEST_STAMINA);

        assertEquals(TEST_NO_STAMINA, testStats.getStamina());
    }

    @Test
    void increaseSpeed_increasesSpeed() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testStats.increaseSpeed(TEST_MODIFY_STATS_AMOUNT);

        assertEquals(TEST_SPEED + TEST_MODIFY_STATS_AMOUNT, testStats.getSpeed());
        assertEquals(TEST_STAMINA, testStats.getStamina());
        assertEquals(TEST_POWER, testStats.getPower());
    }

    @Test
    void increasePower_increasesPower() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testStats.increasePower(TEST_MODIFY_STATS_AMOUNT);

        assertEquals(TEST_SPEED, testStats.getSpeed());
        assertEquals(TEST_STAMINA, testStats.getStamina());
        assertEquals(TEST_POWER + TEST_MODIFY_STATS_AMOUNT, testStats.getPower());
    }

    @Test
    void increaseStamina_increasesStamina() {
        final Stats testStats = new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testStats.increaseStamina(TEST_MODIFY_STATS_AMOUNT);

        assertEquals(TEST_SPEED, testStats.getSpeed());
        assertEquals(TEST_STAMINA + TEST_MODIFY_STATS_AMOUNT, testStats.getStamina());
        assertEquals(TEST_POWER, testStats.getPower());
    }
}
