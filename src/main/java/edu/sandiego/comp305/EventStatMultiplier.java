package edu.sandiego.comp305;

public enum EventStatMultiplier {
    POOR(-0.5, -0.2),
    BAD(-0.3, -0.1),
    OKAY(-0.1, -0.05),
    FAIR(0.1, 0.05),
    GOOD(0.1, 0.5),
    GREAT(0.25, 0.2);

    private final double speedMultiplier;
    private final double powerMultiplier;

    EventStatMultiplier(double speedMultiplier, double powerMultiplier) {
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