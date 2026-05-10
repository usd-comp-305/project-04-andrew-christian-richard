package edu.sandiego.comp305;

public class HorseUpgradeSystem implements UpgradeSystem {
    private static final int LEVEL_UP_AMOUNT = 1;

    @Override
    public void levelUpHorse(Horse horse) {
        horse.getStats().increaseSpeed(LEVEL_UP_AMOUNT);
        horse.getStats().increaseStamina(LEVEL_UP_AMOUNT);
        horse.getStats().increasePower(LEVEL_UP_AMOUNT);
    }

    @Override
    public void applyUpgrade(Horse horse, String statType, int amount) {
        switch (statType.toLowerCase()) {
            case "speed":
                horse.getStats().increaseSpeed(amount);
                break;
            case "stamina":
                horse.getStats().increaseStamina(amount);
                break;
            case "power":
                horse.getStats().increasePower(amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid stat type: " + statType);
        }
    }

    @Override
    public void awardTrophies(Horse horse, Placement placement) {
        horse.addTrophies(getTrophyValuesByPlacement(placement));
    }

    private int getTrophyValuesByPlacement(Placement placement) {
        return switch (placement) {
            case FIRST -> 5;
            case SECOND -> 4;
            case THIRD -> 3;
            case FOURTH -> 2;
            case FIFTH -> 1;
            default -> 0;
        };
    }
}
