package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class HorseUpgradeSystemTests {
    private static final int TEST_SPEED = 1;
    private static final int TEST_POWER = 1;
    private static final int TEST_STAMINA = 1;

    private static final int TEST_LEVEL_UP_SPEED = 2;
    private static final int TEST_LEVEL_UP_POWER = 2;
    private static final int TEST_LEVEL_UP_STAMINA = 2;

    private static final int TEST_MODIFY_STATS_AMOUNT = 2;

    @Test
    void levelUpHorse_IncreasesStatsByOne() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.levelUpHorse(testHorse);

        assertEquals(TEST_LEVEL_UP_SPEED, testHorse.getStats().getSpeed());
        assertEquals(TEST_LEVEL_UP_POWER, testHorse.getStats().getStamina());
        assertEquals(TEST_LEVEL_UP_STAMINA, testHorse.getStats().getPower());
    }

    @Test
    void applyUpgrade_increasesSpeed() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.applyUpgrade(testHorse, "speed", TEST_MODIFY_STATS_AMOUNT);

        assertEquals(TEST_SPEED + TEST_MODIFY_STATS_AMOUNT, testHorse.getStats().getSpeed());
        assertEquals(TEST_STAMINA, testHorse.getStats().getStamina());
        assertEquals(TEST_POWER, testHorse.getStats().getPower());
    }

    @Test
    void applyUpgrade_increasesPower() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.applyUpgrade(testHorse, "power", TEST_MODIFY_STATS_AMOUNT);

        assertEquals(TEST_SPEED, testHorse.getStats().getSpeed());
        assertEquals(TEST_STAMINA, testHorse.getStats().getStamina());
        assertEquals(TEST_POWER + TEST_MODIFY_STATS_AMOUNT, testHorse.getStats().getPower());
    }

    @Test
    void applyUpgrade_increasesStamina() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.applyUpgrade(testHorse, "stamina", TEST_MODIFY_STATS_AMOUNT);

        assertEquals(TEST_SPEED, testHorse.getStats().getSpeed());
        assertEquals(TEST_STAMINA + TEST_MODIFY_STATS_AMOUNT, testHorse.getStats().getStamina());
        assertEquals(TEST_POWER, testHorse.getStats().getPower());
    }

    @Test
    void applyUpgrade_errorsOnInvalidUpgrade() {
        final Horse testHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        assertThrows(IllegalArgumentException.class, () -> testUpgradeSystem.applyUpgrade(testHorse, "invalidUpgrade", TEST_MODIFY_STATS_AMOUNT));
    }

    @Test
    void awardTrophies_awardsCorrectTrophyAmount_PlacementBased() {
        final int FIRST_PLACE_TROPHIES_TO_ADD = 5;
        final int SECOND_PLACE_TROPHIES_TO_ADD = 4;
        final int THIRD_PLACE_TROPHIES_TO_ADD = 3;
        final int FOURTH_PLACE_TROPHIES_TO_ADD = 2;
        final int FIFTH_PLACE_TROPHIES_TO_ADD = 1;
        final int SIXTH_PLACE_TROPHIES_TO_ADD = 0;

        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        final Horse firstPlaceHorse = new Horse("Nunu", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse secondPlaceHorse = new Horse("Christ", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse thirdPlaceHorse = new Horse("Ricardo", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse fourthPlaceHorse = new Horse("Brick", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse fifthPlaceHorse = new Horse("Dino", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse sixthPlaceHorse = new Horse("Bray", new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));

        testUpgradeSystem.awardTrophies(firstPlaceHorse, Placement.FIRST);
        testUpgradeSystem.awardTrophies(secondPlaceHorse, Placement.SECOND);
        testUpgradeSystem.awardTrophies(thirdPlaceHorse, Placement.THIRD);
        testUpgradeSystem.awardTrophies(fourthPlaceHorse, Placement.FOURTH);
        testUpgradeSystem.awardTrophies(fifthPlaceHorse, Placement.FIFTH);
        testUpgradeSystem.awardTrophies(sixthPlaceHorse, Placement.SIXTH);

        assertEquals(FIRST_PLACE_TROPHIES_TO_ADD, firstPlaceHorse.getTrophyCount());
        assertEquals(SECOND_PLACE_TROPHIES_TO_ADD, secondPlaceHorse.getTrophyCount());
        assertEquals(THIRD_PLACE_TROPHIES_TO_ADD, thirdPlaceHorse.getTrophyCount());
        assertEquals(FOURTH_PLACE_TROPHIES_TO_ADD, fourthPlaceHorse.getTrophyCount());
        assertEquals(FIFTH_PLACE_TROPHIES_TO_ADD, fifthPlaceHorse.getTrophyCount());
        assertEquals(SIXTH_PLACE_TROPHIES_TO_ADD, sixthPlaceHorse.getTrophyCount());
    }
}
