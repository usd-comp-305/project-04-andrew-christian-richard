package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatsTests {
    @Test
    void generateMovement_returnedDistanceBetweenPowerAndSpeed() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testStamina = 3;

        final Stats testStats =
                new Stats(testSpeed, testStamina, testPower);

        final int distanceRan = testStats.generateMovement();

        assertTrue(distanceRan >= testPower);
        assertTrue(distanceRan <= testSpeed);
    }

    @Test
    void generateMovement_returnsZero_whenNoStamina() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testNoStamina = 0;

        final Stats testStats =
                new Stats(testSpeed, testNoStamina, testPower);

        final int distanceRan = testStats.generateMovement();

        assertEquals(testNoStamina, distanceRan);
    }

    @Test
    void generateMovement_keepsStaminaUnchanged() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testStamina = 3;

        final Stats testStats =
                new Stats(testSpeed, testStamina, testPower);

        testStats.generateMovement();

        assertEquals(testStamina, testStats.getStamina());
    }

    @Test
    void consumeStamina_depletesStaminaToZero() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testStamina = 3;
        final int testNoStamina = 0;

        final Stats testStats =
                new Stats(testSpeed, testStamina, testPower);

        testStats.consumeStamina(testStamina);
        testStats.consumeStamina(testStamina);

        assertEquals(testNoStamina, testStats.getStamina());
    }

    @Test
    void increaseSpeed_increasesSpeed() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testStamina = 3;
        final int testModifyStatsAmount = 2;

        final Stats testStats =
                new Stats(testSpeed, testStamina, testPower);

        testStats.increaseSpeed(testModifyStatsAmount);

        assertEquals(
                testSpeed + testModifyStatsAmount,
                testStats.getSpeed());

        assertEquals(testStamina, testStats.getStamina());
        assertEquals(testPower, testStats.getPower());
    }

    @Test
    void increasePower_increasesPower() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testStamina = 3;
        final int testModifyStatsAmount = 2;

        final Stats testStats =
                new Stats(testSpeed, testStamina, testPower);

        testStats.increasePower(testModifyStatsAmount);

        assertEquals(testSpeed, testStats.getSpeed());
        assertEquals(testStamina, testStats.getStamina());

        assertEquals(
                testPower + testModifyStatsAmount,
                testStats.getPower());
    }

    @Test
    void increaseStamina_increasesStamina() {
        final int testSpeed = 6;
        final int testPower = 3;
        final int testStamina = 3;
        final int testModifyStatsAmount = 2;

        final Stats testStats =
                new Stats(testSpeed, testStamina, testPower);

        testStats.increaseStamina(testModifyStatsAmount);

        assertEquals(testSpeed, testStats.getSpeed());

        assertEquals(
                testStamina + testModifyStatsAmount,
                testStats.getStamina());

        assertEquals(testPower, testStats.getPower());
    }
}
