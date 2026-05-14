package edu.sandiego.comp305;

public enum EventStatMultiplier {
    VERYBAD(-0.8, -0.7),
    BAD(-0.5, -0.5),
    OKAY(-0.25, -0.25),
    FAIR(0.1, 0.1),
    GOOD(0.5, 0.25),
    GREAT(0.7, 0.5);

    private final double speedMultiplier;

    private final double powerMultiplier;

    EventStatMultiplier(
            final double speedMultiplier,
            final double powerMultiplier) {
        this.speedMultiplier = speedMultiplier;
        this.powerMultiplier = powerMultiplier;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public double getPowerMultiplier() {
        return powerMultiplier;
    }
}
