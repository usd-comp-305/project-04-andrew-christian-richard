package edu.sandiego.comp305;

public interface UpgradeSystem {
    public abstract void levelUpHorse(Horse horse);

    public abstract void applyUpgrade(
            Horse horse,
            String upgradeInput);

    public abstract void awardTrophies(
            Horse horse,
            Placement placement);
}
