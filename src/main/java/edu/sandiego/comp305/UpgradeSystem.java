package edu.sandiego.comp305;

public interface UpgradeSystem {
    void levelUpHorse(Horse horse);
    void applyUpgrade(Horse horse, String statType, int amount);
    void awardTrophies(Horse horse, Placement placement);
}
