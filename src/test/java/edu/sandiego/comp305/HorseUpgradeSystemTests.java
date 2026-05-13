package edu.sandiego.comp305;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class HorseUpgradeSystemTests {
    private static final int TEST_SPEED = 1;

    private static final int TEST_POWER = 1;

    private static final int TEST_STAMINA = 1;

    private static final int TEST_LEVEL_UP_SPEED = 2;

    private static final int TEST_LEVEL_UP_POWER = 2;

    private static final int TEST_LEVEL_UP_STAMINA = 2;

    private Horse testHorse;

    @BeforeEach
    void init() {
        final Stats testStats =
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER);

        testHorse = new Horse("Nunu", testStats);
    }

    @Test
    void levelUpHorse_IncreasesStatsByOne() {
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.levelUpHorse(testHorse);

        assertEquals(TEST_LEVEL_UP_SPEED, testHorse.getStats().getSpeed());
        assertEquals(TEST_LEVEL_UP_POWER, testHorse.getStats().getStamina());
        assertEquals(TEST_LEVEL_UP_STAMINA, testHorse.getStats().getPower());
    }

    @Test
    void applyUpgrade_increasesEachStatInputted() {
        final int speedUpgrade = 1;
        final int staminaUpgrade = 2;
        final int powerUpgrade = 2;
        final String upgradeInput = "1 2 2";

        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.awardTrophies(testHorse, Placement.FIRST);

        testUpgradeSystem.applyUpgrade(testHorse, upgradeInput);

        assertEquals(
                TEST_SPEED + speedUpgrade,
                testHorse.getStats().getSpeed());

        assertEquals(
                TEST_STAMINA + staminaUpgrade,
                testHorse.getStats().getStamina());

        assertEquals(
                TEST_POWER + powerUpgrade,
                testHorse.getStats().getPower());

        assertEquals(0, testHorse.getCurrentUpgradePoints());
    }

    @Test
    void applyUpgrade_errorsIncompleteInput() {
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        assertThrows(
                IllegalArgumentException.class,
                () -> testUpgradeSystem.applyUpgrade(testHorse, "1 1"));
    }

    @Test
    void applyUpgrade_errorsNonIntegerInput() {
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        assertThrows(
                IllegalArgumentException.class,
                () -> testUpgradeSystem.applyUpgrade(testHorse, "1 bruh 2"));
    }

    @Test
    void applyUpgrade_errorsUpgradePointsDoNotEqualAwardedTrophies() {
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.awardTrophies(testHorse, Placement.FIRST);

        assertThrows(
                IllegalArgumentException.class,
                () -> testUpgradeSystem.applyUpgrade(testHorse, "1 1 1"));
    }

    @Test
    void applyUpgrade_UpgradePointsEqualsSecondPlaceTrophies() {
        final int speedUpgrade = 1;
        final int staminaUpgrade = 1;
        final int powerUpgrade = 2;
        final String upgradeInput = "1 1 2";

        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.awardTrophies(testHorse, Placement.SECOND);

        testUpgradeSystem.applyUpgrade(testHorse, upgradeInput);

        assertEquals(
                TEST_SPEED + speedUpgrade,
                testHorse.getStats().getSpeed());

        assertEquals(
                TEST_STAMINA + staminaUpgrade,
                testHorse.getStats().getStamina());

        assertEquals(
                TEST_POWER + powerUpgrade,
                testHorse.getStats().getPower());
    }

    @Test
    void applyUpgrade_usesCurrentTrophies_NotTotalTrophies() {
        final int speedUpgrade = 0;
        final int staminaUpgrade = 1;
        final int powerUpgrade = 0;
        final String upgradeInput = "0 1 0";

        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        testUpgradeSystem.awardTrophies(testHorse, Placement.FIRST);
        testUpgradeSystem.applyUpgrade(testHorse, "1 2 2");

        testUpgradeSystem.awardTrophies(testHorse, Placement.FIFTH);

        testUpgradeSystem.applyUpgrade(testHorse, upgradeInput);

        assertEquals(
                Placement.FIRST.getTrophyValue()
                        + Placement.FIFTH.getTrophyValue(),
                testHorse.getTrophyCount());

        assertEquals(
                TEST_SPEED + 1 + speedUpgrade,
                testHorse.getStats().getSpeed());

        assertEquals(
                TEST_STAMINA + 2 + staminaUpgrade,
                testHorse.getStats().getStamina());

        assertEquals(
                TEST_POWER + 2 + powerUpgrade,
                testHorse.getStats().getPower());
    }

    @Test
    void awardTrophies_awardsCorrectTrophyAmount_PlacementBased() {
        final HorseUpgradeSystem testUpgradeSystem = new HorseUpgradeSystem();

        final Horse firstPlaceHorse = new Horse(
                "Nunu",
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse secondPlaceHorse = new Horse(
                "Christ",
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse thirdPlaceHorse = new Horse(
                "Ricardo",
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse fourthPlaceHorse = new Horse(
                "Brick",
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse fifthPlaceHorse = new Horse(
                "Dino",
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));
        final Horse sixthPlaceHorse = new Horse(
                "Bray",
                new Stats(TEST_SPEED, TEST_STAMINA, TEST_POWER));

        testUpgradeSystem.awardTrophies(firstPlaceHorse, Placement.FIRST);
        testUpgradeSystem.awardTrophies(secondPlaceHorse, Placement.SECOND);
        testUpgradeSystem.awardTrophies(thirdPlaceHorse, Placement.THIRD);
        testUpgradeSystem.awardTrophies(fourthPlaceHorse, Placement.FOURTH);
        testUpgradeSystem.awardTrophies(fifthPlaceHorse, Placement.FIFTH);
        testUpgradeSystem.awardTrophies(sixthPlaceHorse, Placement.SIXTH);

        assertEquals(
                Placement.FIRST.getTrophyValue(),
                firstPlaceHorse.getTrophyCount());

        assertEquals(
                Placement.SECOND.getTrophyValue(),
                secondPlaceHorse.getTrophyCount());

        assertEquals(
                Placement.THIRD.getTrophyValue(),
                thirdPlaceHorse.getTrophyCount());

        assertEquals(
                Placement.FOURTH.getTrophyValue(),
                fourthPlaceHorse.getTrophyCount());

        assertEquals(
                Placement.FIFTH.getTrophyValue(),
                fifthPlaceHorse.getTrophyCount());

        assertEquals(
                Placement.SIXTH.getTrophyValue(),
                sixthPlaceHorse.getTrophyCount());
    }
}
