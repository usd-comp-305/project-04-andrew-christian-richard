package edu.sandiego.comp305;

public class HorseUpgradeSystem implements UpgradeSystem {
    private static final int LEVEL_UP_AMOUNT = 1;

    private static final int SPEED_UPGRADE_INDEX = 0;

    private static final int POWER_UPGRADE_INDEX = 1;

    private static final int STAMINA_UPGRADE_INDEX = 2;

    private static final int UPGRADE_COUNT = 3;

    private static final String UPGRADE_SEPARATOR = "\\s+";

    @Override
    public void levelUpHorse(final Horse horse) {
        horse.getStats().increaseSpeed(LEVEL_UP_AMOUNT);
        horse.getStats().increaseStamina(LEVEL_UP_AMOUNT);
        horse.getStats().increasePower(LEVEL_UP_AMOUNT);
    }

    @Override
    public void applyUpgrade(final Horse horse, final String upgradeInput) {
        final String[] upgradeAmounts =
                upgradeInput.trim().split(UPGRADE_SEPARATOR);

        validateUpgradeInput(upgradeAmounts);

        final int speedUpgrade =
                parseUpgradeAmount(upgradeAmounts[SPEED_UPGRADE_INDEX]);

        final int staminaUpgrade =
                parseUpgradeAmount(upgradeAmounts[STAMINA_UPGRADE_INDEX]);

        final int powerUpgrade =
                parseUpgradeAmount(upgradeAmounts[POWER_UPGRADE_INDEX]);

        validateUpgradeTotal(
                horse.getCurrentUpgradePoints(),
                speedUpgrade,
                staminaUpgrade,
                powerUpgrade);

        horse.getStats().increaseSpeed(speedUpgrade);

        horse.getStats().increaseStamina(staminaUpgrade);

        horse.getStats().increasePower(powerUpgrade);
    }

    @Override
    public void awardTrophies(final Horse horse, final Placement placement) {
        horse.addTrophies(placement.getTrophyValue());
        horse.changeCurrentUpgradePoints(placement.getTrophyValue());
    }

    private void validateUpgradeInput(final String[] upgradeAmounts) {
        if (upgradeAmounts.length != UPGRADE_COUNT) {
            throw new IllegalArgumentException(
                    "Upgrade input must include speed, stamina, and power.");
        }
    }

    private int parseUpgradeAmount(final String upgradeAmount) {
        try {
            return Integer.parseInt(upgradeAmount);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Upgrade amounts must be whole numbers.",
                    exception);
        }
    }

    private void validateUpgradeTotal(
            final int upgradePoints,
            final int speedUpgrade,
            final int staminaUpgrade,
            final int powerUpgrade) {
        final int totalUpgradePoints =
                speedUpgrade + staminaUpgrade + powerUpgrade;

        if (speedUpgrade < 0 || staminaUpgrade < 0 || powerUpgrade < 0) {
            throw new IllegalArgumentException(
                    "Upgrade amounts cannot be negative.");
        }

        if (totalUpgradePoints != upgradePoints) {
            throw new IllegalArgumentException(
                    "Upgrade amounts must add up to " + upgradePoints);
        }
    }
}
