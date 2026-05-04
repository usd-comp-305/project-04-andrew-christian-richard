package edu.sandiego.comp305;

public class RaceEffect {
    private final int speedChange;
    private final int powerChange;

    public RaceEffect(int speedChange, int powerChange) {
        this.speedChange = speedChange;
        this.powerChange = powerChange;
    }

    public int getPowerChange() {
        return powerChange;
    }

    public int getSpeedChange() {
        return speedChange;
    }

    @Override
    public String toString() {
        return "Your speed and power has temporarily changed by " + speedChange +
                " and " + powerChange;
    }
}
