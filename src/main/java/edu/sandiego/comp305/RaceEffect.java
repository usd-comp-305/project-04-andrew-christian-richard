package edu.sandiego.comp305;

public class RaceEffect {
    private static final int NO_CHANGE = 0;

    public static final RaceEffect NO_EFFECT =
            new RaceEffect(NO_CHANGE, NO_CHANGE);

    private final int speedChange;

    private final int powerChange;

    public RaceEffect(final int speedChange, final int powerChange) {
        this.speedChange = speedChange;
        this.powerChange = powerChange;
    }

    public int getPowerChange() {
        return powerChange;
    }

    public int getSpeedChange() {
        return speedChange;
    }

    public void apply(final Horse horse) {
        horse.applyRaceEffect(this);
    }

    @Override
    public String toString() {
        return "Your speed and power has temporarily changed by "
                + speedChange
                + " and "
                + powerChange;
    }
}
